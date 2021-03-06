package productshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductInRangeDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String seller;

    public ProductInRangeDto() {
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getSeller() {
        return this.seller;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
