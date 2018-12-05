package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RacerImportDto {
    @Expose
    private String name;
    @Expose
    private Integer age;
    @Expose
    private BigDecimal bounty;
    @Expose
    private String homeTown;

    public RacerImportDto() {
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public BigDecimal getBounty() {
        return this.bounty;
    }

    public String getHomeTown() {
        return this.homeTown;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
