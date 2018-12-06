package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.List;

@Entity(name = "towns")
public class Town extends BaseEntity {

    private String name;
    private List<Racer> racers;

    public Town() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    @OneToMany(targetEntity = Racer.class, mappedBy = "homeTown")
    public List<Racer> getRacer() {
        return this.racers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRacer(List<Racer> racers) {
        this.racers = racers;
    }
}
