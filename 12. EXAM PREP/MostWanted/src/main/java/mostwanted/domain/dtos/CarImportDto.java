package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CarImportDto {
    @Expose
    private String brand;
    @Expose
    private String model;
    @Expose
    private BigDecimal price;
    @Expose
    private Integer yearOfProduction;
    @Expose
    private Double maxSpeed;
    @Expose
    private Double zeroToSixty;
    @Expose
    private String racerName;

    public CarImportDto() {
    }

    @NotNull
    public String getBrand() {
        return this.brand;
    }

    @NotNull
    public String getModel() {
        return this.model;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    @NotNull
    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    public Double getMaxSpeed() {
        return this.maxSpeed;
    }

    public Double getZeroToSixty() {
        return this.zeroToSixty;
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
