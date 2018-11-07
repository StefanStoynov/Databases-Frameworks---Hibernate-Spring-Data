package app.domein.entities.p04_hospitaldatabase;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name= "visitations")
public class Visitation extends BaseEntity {

    private LocalDate date;
    private String comment;
    private Set<Patient> patient;

    public Visitation() {
    }

    @Column(name = "date", nullable = false)
    @Positive
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Column(name = "comment", nullable = false, length = 1000)
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @ManyToMany(targetEntity = Patient.class, mappedBy = "visitations")
    public Set<Patient> getPatient() {
        return this.patient;
    }

    public void setPatient(Set<Patient> patient) {
        this.patient = patient;
    }
}
