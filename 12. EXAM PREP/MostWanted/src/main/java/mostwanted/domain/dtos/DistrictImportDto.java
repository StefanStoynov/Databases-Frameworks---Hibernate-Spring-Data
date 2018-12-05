package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class DistrictImportDto {

    @Expose
    private String name;
    @Expose
    private String townName;

    public DistrictImportDto() {
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
