package app.domein.entities.p04_hospitaldatabase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "diagnoses")
public class Diagnose extends BaseEntity{
    private String name;
    private String comment;
    private Set<Patient> patients;

    public Diagnose() {
    }
    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "comment")
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @OneToMany(targetEntity = Patient.class, mappedBy = "id")
    public Set<Patient> getPatients() {
        return this.patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
