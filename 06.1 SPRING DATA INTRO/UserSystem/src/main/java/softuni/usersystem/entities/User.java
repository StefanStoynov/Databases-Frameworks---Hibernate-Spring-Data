package softuni.usersystem.entities;

import softuni.usersystem.validators.Email;
import softuni.usersystem.validators.Password;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private byte[] profilePicture;
    private Date registeredOn;
    private Date lastTimeLoggedIn;
    private Short age;
    private Boolean isDeleted;
    private Town townOfBirth;
    private Town townOfResidence;
    private Set<User> friends;
    private Set<Album> albums;

    public User() {
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Size(min = 4, max = 30)
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Password(minLength = 6, maxLength = 50, containsLowercase = true, containsDigit = true,
            containsSpecialSymbol = true, containsUppercase = true)
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Email
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Lob
    @Size(max = 1024 * 1024)
    @Column(name = "profile_picture")
    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Column(name = "registered_on")
    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Column(name = "last_time_logged_in")
    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Min(1)
    @Max(120)
    @Column(name = "age")
    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    @Column(name = "is_deleted")
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @ManyToOne
    @JoinColumn(name = "town_of_birth_id", referencedColumnName = "id")
    public Town getTownOfBirth() {
        return townOfBirth;
    }

    public void setTownOfBirth(Town townOfBirth) {
        this.townOfBirth = townOfBirth;
    }

    @ManyToOne
    @JoinColumn(name = "town_of_residence_id", referencedColumnName = "id")
    public Town getTownOfResidence() {
        return townOfResidence;
    }

    public void setTownOfResidence(Town townOfResidence) {
        this.townOfResidence = townOfResidence;
    }

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "user", targetEntity = Album.class)
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
