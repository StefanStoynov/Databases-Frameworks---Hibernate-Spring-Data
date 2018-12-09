package alararestaurant.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity(name = "employees")
public class Employee extends BaseEntity{

    private String name;
    private Integer age;
    private Position position;
    private List<Order> orders;

    public Employee() {
    }

    @Column(name = "name", nullable = false)
    @Length(min = 3 , max = 30)
    public String getName() {
        return this.name;
    }

    @Column(name = "age", nullable = false)
    @Min(value = 15)
    @Max(value = 80)
    public Integer getAge() {
        return this.age;
    }

    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    public Position getPosition() {
        return this.position;
    }

    @OneToMany(targetEntity = Order.class, mappedBy = "employee")
    public List<Order> getOrders() {
        return this.orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
