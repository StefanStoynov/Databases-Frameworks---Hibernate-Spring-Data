package softuni.usersystem.services;

import org.springframework.stereotype.Service;
import softuni.usersystem.entities.User;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> findUsersByDomain(String domain);

    void RemoveInactiveUsers(Date date);
}
