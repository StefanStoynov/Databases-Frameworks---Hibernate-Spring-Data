package gamestore.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class User extends BaseEntity {

    private String emial;
    private String fullName;
    private String password;
    private Set<Game> games;
    private Role role;

    public User() {
    }

    //<editor-fold desc="Getters">
    @Column(name = "email", nullable = false, unique = true)
    public String getEmial() {
        return this.emial;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return this.fullName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    @ManyToMany(targetEntity = Game.class)
    @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    public Set<Game> getGames() {
        return this.games;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role getRole() {
        return this.role;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setEmial(String emial) {
        this.emial = emial;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    //</editor-fold>
}
