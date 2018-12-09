package alararestaurant.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class PositionImportDto {
    @Expose
    private String name;

    public PositionImportDto(String name) {
        this.name = name;
    }

    @NotNull
    @Length(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
