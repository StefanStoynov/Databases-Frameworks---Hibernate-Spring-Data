package carshop.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "customers")
public class Customer extends BaseEntity {
    private String name;
    private LocalDate birthDate;
    private boolean isYangDriver;

    public Customer() {
    }

    //<editor-fold desc="Getters">
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "birth_date")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Column(name = "is_yang_driver")
    public boolean isYangDriver() {
        return isYangDriver;
    }
    //</editor-fold>


    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setYangDriver(boolean yangDriver) {
        isYangDriver = yangDriver;
    }
    //</editor-fold>
}
