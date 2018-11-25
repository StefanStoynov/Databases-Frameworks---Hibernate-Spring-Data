package gamestore.web.controller;

import gamestore.domain.dtos.AddGameDto;
import gamestore.domain.dtos.UserLoginDto;
import gamestore.domain.dtos.UserLogoutDto;
import gamestore.domain.dtos.UserRegisterDto;
import gamestore.service.GameService;
import gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private String loginUserEmail;

    @Autowired
    public GameStoreController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
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
                        if (this.loginUserEmail!= null && this.userService.isAdmin(this.loginUserEmail)){
                            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(params[2]));
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            String dateAsString = params[7];
                            LocalDate date = LocalDate.parse(dateAsString,formatter);
                            AddGameDto addGameDto = new AddGameDto(params[1], price ,Double.parseDouble(params[3]),params[4],params[5],params[6],date);
                            System.out.println(this.gameService.addGame(addGameDto));
                        }else{
                            System.out.println("Cannot add Game. No user was logged in.");
                        }
                    break;

            }
        }
    }
}
