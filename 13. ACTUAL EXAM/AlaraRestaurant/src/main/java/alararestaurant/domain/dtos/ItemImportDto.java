package alararestaurant.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemImportDto {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String category;

    public ItemImportDto() {
    }

    @NotNull
    @Length(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    @NotNull
    @DecimalMin(value = "0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    @NotNull
    public String getCategory() {
        return this.category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
