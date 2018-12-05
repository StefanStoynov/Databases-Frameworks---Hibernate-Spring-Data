package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "races")
public class Race extends BaseEntity{

    private Integer laps;
    private District district;
    private List<RaceEntry> entries;

    public Race() {
    }
    @Column(name = "laps", nullable = false, columnDefinition = "int default 0")
    public Integer getLaps() {
        return this.laps;
    }

    @ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    public District getDistrict() {
        return this.district;
    }

    @OneToMany(targetEntity = RaceEntry.class, mappedBy = "race")
    public List<RaceEntry> getEntries() {
        return this.entries;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}
