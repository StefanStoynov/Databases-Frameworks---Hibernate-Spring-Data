package productshop.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import productshop.domain.entities.User;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductSeedDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private User seller;
    @Expose
    private User buyer;

    public ProductSeedDto() {
    }

    //<editor-fold desc="Getters">
    @Length(min = 3, message = "Minimum length of name field is 3 symbols.")
    public String getName() {
        return this.name;
    }

    @NotNull(message = "Price field cannot be empty.")
    public BigDecimal getPrice() {
        return this.price;
    }

    public User getSeller() {
        return this.seller;
    }

    public User getBuyer() {
        return this.buyer;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
    //</editor-fold>
}
