package gamestore.service;

import gamestore.domain.dtos.UserRegisterDto;
import gamestore.domain.entities.Role;
import gamestore.domain.entities.User;
import gamestore.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            User entity = this.modelMapper.map(userRegisterDto, User.class);

            if (this.userRepository.count() == 0) {
                entity.setRole(Role.ADMIN);
            } else {
                entity.setRole(Role.USER);
            }
            this.userRepository.saveAndFlush(entity);
            sb.append(String.format("%s was registered",entity.getFullName()));
            System.out.println(userRepository.count());
        }
        return sb.toString().trim();
    }
}
