package gamestore.web.controller;

import gamestore.domain.dtos.UserLoginDto;
import gamestore.domain.dtos.UserLogoutDto;
import gamestore.domain.dtos.UserRegisterDto;
import gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {
    private final UserService userService;
    private String loginUserEmail;

    @Autowired
    public GameStoreController(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] params = scanner.nextLine().split("\\|");

            switch (params[0]) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto = new UserRegisterDto(params[1], params[2], params[3], params[4]);
                    System.out.println(this.userService.registeredUser(userRegisterDto));
                    break;
                case "LoginUser":
                    if (loginUserEmail == null) {
                        UserLoginDto userLoginDto = new UserLoginDto(params[1], params[2]);
                        String loginResult = this.userService.loginUser(userLoginDto);

                        if (loginResult.contains("Successfully logged in")) {
                            this.loginUserEmail = userLoginDto.getEmail();
                        }
                        System.out.println(loginResult);
                    } else {
                        System.out.println("Session is taken!");
                    }
                    break;
                case "Logout":
                    if (this.loginUserEmail == null) {
                        System.out.println("Cannot log out. No user was logged in.");
                    } else {
                        String logoutResult = this.userService.logoutUser(new UserLogoutDto(this.loginUserEmail));
                        System.out.println(logoutResult);
                        this.loginUserEmail = null;
                    }

                    break;
                case "AddGame":
                    break;
                case "EditGame":
                    break;
                case "DeleteGame":
                    break;
            }
        }
    }
}
