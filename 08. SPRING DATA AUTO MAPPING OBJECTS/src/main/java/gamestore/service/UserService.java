package gamestore.service;

import gamestore.domain.dtos.UserRegisterDto;

public interface UserService {
    String registeredUser(UserRegisterDto userRegisterDto);
}
