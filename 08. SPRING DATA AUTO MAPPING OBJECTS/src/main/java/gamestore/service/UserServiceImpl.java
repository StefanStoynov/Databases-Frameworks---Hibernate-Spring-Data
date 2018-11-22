package gamestore.service;

import gamestore.domain.dtos.AddGameDto;
import gamestore.domain.dtos.UserLoginDto;
import gamestore.domain.dtos.UserLogoutDto;
import gamestore.domain.dtos.UserRegisterDto;
import gamestore.domain.entities.Game;
import gamestore.domain.entities.Role;
import gamestore.domain.entities.User;
import gamestore.repository.GameRepository;
import gamestore.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registeredUser(UserRegisterDto userRegisterDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        StringBuilder sb = new StringBuilder();
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return "Passwords don`t match";
        } else if (validator.validate(userRegisterDto).size() > 0) {
            for (ConstraintViolation<UserRegisterDto> violation : validator.validate(userRegisterDto)) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            User entity = this.userRepository.findByEmail(userRegisterDto.getEmail()).orElse(null);

            if (entity != null) {
                sb.append("User already exist!");
                return sb.toString();
            }
            entity = this.modelMapper.map(userRegisterDto, User.class);

            if (this.userRepository.count() == 0) {
                entity.setRole(Role.ADMIN);
            } else {
                entity.setRole(Role.USER);
            }
            this.userRepository.saveAndFlush(entity);
            sb.append(String.format("%s was registered", entity.getFullName()));
        }
        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<UserLoginDto>> violations = validator.validate(userLoginDto);

        if (violations.size() > 0) {
            for (ConstraintViolation<UserLoginDto> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            User entity = this.userRepository.findByEmail(userLoginDto.getEmail()).orElse(null);

            if (entity == null) {
                return sb.append("User doesn't exist").toString();
            } else if (!entity.getPassword().equals(userLoginDto.getPassword())) {
                return sb.append("Wong password").toString();
            }

            sb.append(String.format("Successfully logged in %s", entity.getFullName()));
        }
        return sb.toString().trim();
    }

    @Override
    public String logoutUser(UserLogoutDto userLogoutDto) {
        User entity = this.userRepository.findByEmail(userLogoutDto.getEmail()).orElse(null);
        StringBuilder sb = new StringBuilder();
        if (entity == null) {
            return sb.append("User doesn't exist").toString();
        }
        sb.append(String.format("User %s successfully logged out", entity.getFullName()));


        return sb.toString();
    }

    @Override
    public boolean isAdmin(String email) {
        User userEntity = this.userRepository.findByEmail(email).orElse(null);

        if (userEntity != null) {
            return userEntity.getRole().equals(Role.ADMIN);
        }

        return false;
    }


}
