package softuni.usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.usersystem.entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        List<User> findAllByEmailEndsWith(String domain);

        List<User> findAllByLastTimeLoggedInBefore(Date date);

        void deleteAllByIsDeleted(Boolean isDeleted);
    }
