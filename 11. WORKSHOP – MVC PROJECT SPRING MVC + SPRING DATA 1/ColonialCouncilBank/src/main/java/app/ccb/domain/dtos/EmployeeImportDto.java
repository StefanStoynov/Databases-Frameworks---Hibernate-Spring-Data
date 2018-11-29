package app.ccb.domain.dtos;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeImportDto {
    @SerializedName("full_name")
    private String fullName;

    private BigDecimal salary;

    @SerializedName("started_on")
    private String startedOn;

    @SerializedName("branch_name")
    private String branchName;

    public EmployeeImportDto() {
    }

    @NotNull
    public String getFullName() {
        return this.fullName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }


    public String getStartedOn() {
        return this.startedOn;
    }

    @NotNull
    public String getBranchName() {
        return this.branchName;
    }

    //<editor-fold desc="Setters">
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    //</editor-fold>
}
