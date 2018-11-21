package gamestore.web.controller;

import gamestore.domain.dtos.UserRegisterDto;
import gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {
    private final UserService userService;

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
                    UserRegisterDto userRegisterDto = new UserRegisterDto(params[1],params[2],params[3],params[4]);
                    System.out.println(this.userService.registeredUser(userRegisterDto));
                    break;
                case "LoginUser":
                    break;
                case "Logout":
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
