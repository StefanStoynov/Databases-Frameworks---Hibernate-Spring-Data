package app.ccb.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "clients")
public class Client extends BaseEntity{

    private String full_name;
    private Integer age;
    private BankAccount bankAccount;
    private List<Employee> employees;

    public Client() {
    }

    @Column(name = "full_name", nullable = false)
    public String getFull_name() {
        return this.full_name;
    }

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    @OneToOne(targetEntity = BankAccount.class, mappedBy = "client")
    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    @ManyToMany(targetEntity = Employee.class)
    @JoinTable(
            name = "employees_clients",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id")
    )
    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
