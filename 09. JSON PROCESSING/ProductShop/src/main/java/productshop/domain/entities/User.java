package productshop.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<User> friends;

    public User() {
    }

    //<editor-fold desc="Getters">
    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }


    @ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    public List<User> getFriends() {
        return this.friends;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setFirstName(String firtName) {
        this.firstName = firtName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
    //</editor-fold>
}
