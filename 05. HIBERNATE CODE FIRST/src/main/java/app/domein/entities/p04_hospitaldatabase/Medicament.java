package app.domein.entities.p04_hospitaldatabase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "medicaments")
public class Medicament extends BaseEntity{

    private String name;
    private Set<Patient> patients;

    public Medicament() {
    }
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToMany(targetEntity = Patient.class, mappedBy = "medicaments")
    public Set<Patient> getPatients() {
        return this.patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
