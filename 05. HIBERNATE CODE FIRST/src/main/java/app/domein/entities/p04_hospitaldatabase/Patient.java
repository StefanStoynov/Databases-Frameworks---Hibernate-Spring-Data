package app.domein.entities.p04_hospitaldatabase;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "patients")
public class Patient extends BaseEntity {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate dateOfBirth;
    private byte[] picture;
    private boolean isMedicalEnsured;
    private Diagnose diagnose;
    private Set<Visitation> visitations;
    private Set<Medicament> medicaments;

    public Patient() {

    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "email", nullable = false)
    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth", nullable = false)
    @Positive
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "picture")
    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Column(name = "is_medical_ensured", nullable = false)
    public boolean isMedicalEnsured() {
        return this.isMedicalEnsured;
    }

    public void setMedicalEnsured(boolean medicalEnsured) {
        isMedicalEnsured = medicalEnsured;
    }

    @ManyToOne(targetEntity = Diagnose.class)
    @JoinColumn(name = "patients", referencedColumnName = "id")
    public Diagnose getDiagnose() {
        return this.diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    @ManyToMany()
    @JoinTable(name = "patients_visitations"
            , joinColumns = @JoinColumn(name = "visitations", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "patient", referencedColumnName = "id"))
    public Set<Visitation> getVisitations() {
        return this.visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    @ManyToMany()
    @JoinTable(name = "patients_medicaments"
            , joinColumns = @JoinColumn(name = "medicament", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "patient", referencedColumnName = "id"))
    public Set<Medicament> getMedicaments() {
        return this.medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
