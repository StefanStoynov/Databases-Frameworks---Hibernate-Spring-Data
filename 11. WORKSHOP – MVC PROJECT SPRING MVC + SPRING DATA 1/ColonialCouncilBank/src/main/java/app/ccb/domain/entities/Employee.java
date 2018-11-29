package app.ccb.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "employees")
public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate startedOn;
    private Branch branch;
    private List<Client> clients;

    public Employee() {
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return this.firstName;
    }
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return this.lastName;
    }
    @Column(name= "salary")
    public BigDecimal getSalary() {
        return this.salary;
    }
    @Column(name = "started_on")
    public LocalDate getStartedOn() {
        return this.startedOn;
    }

    @ManyToOne(targetEntity = Branch.class)
    @JoinColumn(name = "branc_id", referencedColumnName = "id")
    public Branch getBranch() {
        return this.branch;
    }

    @ManyToMany(targetEntity = Client.class, mappedBy = "employees")
    public List<Client> getClients() {
        return this.clients;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setStartedOn(LocalDate startedOn) {
        this.startedOn = startedOn;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
