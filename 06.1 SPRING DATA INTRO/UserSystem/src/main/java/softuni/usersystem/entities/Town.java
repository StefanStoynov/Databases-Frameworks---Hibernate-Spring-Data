package softuni.usersystem.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    private Integer id;
    private String name;
    private String country;
    private Set<User> bornIn;
    private Set<User> liveIn;

    public Town() {
        this.bornIn = new HashSet<>();
        this.liveIn = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "townOfBirth", targetEntity = User.class)
    public Set<User> getBornIn() {
        return bornIn;
    }

    public void setBornIn(Set<User> bornIn) {
        this.bornIn = bornIn;
    }

    @OneToMany(mappedBy = "townOfResidence", targetEntity = User.class)
    public Set<User> getLiveIn() {
        return liveIn;
    }

    public void setLiveIn(Set<User> liveIn) {
        this.liveIn = liveIn;
    }
}
