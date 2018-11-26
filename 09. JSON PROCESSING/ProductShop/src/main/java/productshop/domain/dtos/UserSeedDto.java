package productshop.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserSeedDto implements Serializable {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;

    public UserSeedDto() {
    }

    //<editor-fold desc="Getters">

    public String getFirstName() {
        return this.firstName;
    }

    @Length(min = 3, message = "Minimum length of Last Name field is 3 symbols.")
    @NotNull(message = "Last Name field cannot be empty.")
    public String getLastName() {
        return this.lastName;
    }

    public Integer getAge() {
        return this.age;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    //</editor-fold>
}
