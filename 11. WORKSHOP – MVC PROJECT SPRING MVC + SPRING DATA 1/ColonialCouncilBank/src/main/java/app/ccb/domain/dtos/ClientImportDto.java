package app.ccb.domain.dtos;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class ClientImportDto {

    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private Integer age;
    @SerializedName("appointed_employee")
    private String appointedEmployeeFullName;

    public ClientImportDto() {
    }

    @NotNull
    public String getFirstName() {
        return this.firstName;
    }
    @NotNull
    public String getLastName() {
        return this.lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getAppointedEmployeeFullName() {
        return this.appointedEmployeeFullName;
    }

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

    public void setAppointedEmployeeFullName(String appointedEmployeeFullName) {
        this.appointedEmployeeFullName = appointedEmployeeFullName;
    }
    //</editor-fold>
}
