package alararestaurant.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EmployeeImportDto {

    @Expose
    private String name;
    @Expose
    private Integer age;
    @Expose
    private String position;

    public EmployeeImportDto() {
    }

    @NotNull
    @Length(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    @NotNull
    @Min(value = 15)
    @Max(value = 80)
    public Integer getAge() {
        return this.age;
    }

    @NotNull
    public String getPosition() {
        return this.position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
