package productshop.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshop.domain.dtos.UserSeedDto;
import productshop.domain.entities.User;
import productshop.repository.UserRepository;
import productshop.util.ValidatorUtil;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDto) {
        for (UserSeedDto seedDto : userSeedDto) {
            if (!this.validatorUtil.isValid(seedDto)){
                this.validatorUtil
                        .violations(seedDto)
                        .forEach( violetion -> System.out.println(violetion.getMessage()));
                continue;
            }


            User entity = this.modelMapper.map(seedDto,User.class);

            this.userRepository.saveAndFlush(entity);
        }
    }
}
