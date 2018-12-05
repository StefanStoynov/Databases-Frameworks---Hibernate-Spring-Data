package carshop.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sales")
public class Sale extends BaseEntity {
    private Integer percentage;
    private Car car;
    private Customer customer;

    public Sale() {
    }

    //<editor-fold desc="Getters">
    @Column(name = "percentage")
    public Integer getPercentage() {
        return percentage;
    }

    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car getCar() {
        return car;
    }

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer getCustomer() {
        return customer;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    //</editor-fold>
}
