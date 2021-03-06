package app.domein.entities.p03_universitysystem;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Person() {
    }

    @Column(name ="first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name ="last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name ="phone_number")
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
