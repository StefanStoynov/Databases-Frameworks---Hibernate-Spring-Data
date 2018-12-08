package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntrieImportDto {

    @XmlAttribute(name = "has-finished")
    private Boolean hasFinished;
    @XmlAttribute(name = "finish-time")
    private Double finishTime;
    @XmlAttribute(name = "car-id")
    private Integer carId;
    @XmlElement(name = "racer")
    private String racer;

    public RaceEntrieImportDto() {
    }

    public Boolean getHasFinished() {
        return this.hasFinished;
    }

    public Double getFinishTime() {
        return this.finishTime;
    }

    public Integer getCarId() {
        return this.carId;
    }

    public String getRacer() {
        return this.racer;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public void setRacer(String racer) {
        this.racer = racer;
    }
}
