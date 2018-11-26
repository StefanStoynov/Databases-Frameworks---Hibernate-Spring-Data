package productshop.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class CategorySeedDto {
    @Expose
    private String name;

    public CategorySeedDto() {
    }

    @Length(min = 3, max = 15, message = "Minimum length is 3 symbols and maximum length is 15 symbols of name field")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
