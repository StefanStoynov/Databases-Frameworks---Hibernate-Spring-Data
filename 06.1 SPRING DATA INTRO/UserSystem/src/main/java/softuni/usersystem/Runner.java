package softuni.usersystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.usersystem.entities.User;
import softuni.usersystem.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class Runner implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public Runner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws IOException, ParseException {
        GetUsersByEmailProvider();
        //RemoveInactiveUsers();
    }

    private void RemoveInactiveUsers() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        Date date = df.parse(input);
        userService.RemoveInactiveUsers(date);
}

    private void GetUsersByEmailProvider() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String domain = "@" + input;
        List<User> users = userService.findUsersByDomain(domain);
        if (users.size() == 0) {
            System.out.println("No users found with email domain " + input);
        } else {
            users.forEach(x -> System.out.printf("%s %s%n", x.getUsername(), x.getEmail()));
        }
    }
}
