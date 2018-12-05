package carshop.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "parts")
public class CarPart extends BaseEntity {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private List<Car> cars;
    private Supplier supplier;

    public CarPart() {
    }

    //<editor-fold desc="Getters">
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @ManyToMany(targetEntity = Car.class)
    @JoinTable(
            name = "parts_cars",
            joinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id")
    )
    public List<Car> getCars() {
        return cars;
    }

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    public Supplier getSupplier() {
        return supplier;
    }


//</editor-fold>

    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    //</editor-fold>


}
