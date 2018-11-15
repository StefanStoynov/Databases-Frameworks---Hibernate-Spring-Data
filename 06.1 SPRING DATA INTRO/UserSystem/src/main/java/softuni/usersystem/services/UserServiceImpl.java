package softuni.usersystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.usersystem.entities.User;
import softuni.usersystem.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findUsersByDomain(String domain) {
        return userRepository.findAllByEmailEndsWith(domain);
    }

    @Override
    public void RemoveInactiveUsers(Date date) {
        List<User> users = userRepository.findAllByLastTimeLoggedInBefore(date);
        if (users.size() == 0) {
            System.out.println("No users have been deleted");
            return;
        }
        for (User user : users) {
            user.setIsDeleted(true);
        }
        userRepository.saveAll(users);
        System.out.printf("%s users have been deleted%n", users.size());
        userRepository.deleteAllByIsDeleted(true);
    }
}
