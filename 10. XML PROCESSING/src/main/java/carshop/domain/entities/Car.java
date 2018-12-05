package carshop.domain.entities;

import org.springframework.data.repository.query.parser.Part;

import javax.persistence.*;
import java.util.List;

@Entity(name = "cars")
public class Car extends BaseEntity {

    private String make;
    private String model;
    private Long traveledDistance;
    private List<Part> parts;
    private List<Sale> sales;


    public Car() {
    }

    //<editor-fold desc="Getters">
    @Column(name = "make")
    public String getMake() {
        return make;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    @Column(name = "traveled_distance")
    public Long getTraveledDistance() {
        return traveledDistance;
    }

    @ManyToMany(targetEntity = CarPart.class, mappedBy = "cars")
    public List<Part> getParts() {
        return parts;
    }

    @OneToMany(targetEntity = Sale.class, mappedBy = "car")
    public List<Sale> getSales() {
        return sales;
    }

    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTraveledDistance(Long traveledDistance) {
        this.traveledDistance = traveledDistance;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    //</editor-fold>


}
