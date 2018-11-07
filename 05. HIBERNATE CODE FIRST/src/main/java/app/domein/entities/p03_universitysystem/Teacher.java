package app.domein.entities.p03_universitysystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "teachers")
public class Teacher extends Person {
    private String email;
    private BigDecimal salaryPerHour;
    private Set<Course> courses;

    public Teacher() {
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "salry_per_hour")
    public BigDecimal getSalaryPerHour() {
        return this.salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @OneToMany(targetEntity = Course.class, mappedBy = "teacher")
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
