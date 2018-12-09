package alararestaurant.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "positions")
public class Position extends BaseEntity {

    private String name;
    private Set<Employee> employees;

    public Position() {
    }

    @Column(name = "name", nullable = false, unique = true)
    @Length(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    @OneToMany(targetEntity = Employee.class, mappedBy = "position")
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
