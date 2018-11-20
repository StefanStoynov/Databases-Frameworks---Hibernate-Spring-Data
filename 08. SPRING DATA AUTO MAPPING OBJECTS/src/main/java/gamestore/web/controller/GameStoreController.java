package gamestore.web.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true){
            String[] params = scanner.nextLine().split("\\|");

            switch (params[0]){
                case "RegisterUser":
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
