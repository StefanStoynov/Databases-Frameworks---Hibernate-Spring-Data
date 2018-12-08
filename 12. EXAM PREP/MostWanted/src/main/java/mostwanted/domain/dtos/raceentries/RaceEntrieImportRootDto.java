package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntrieImportRootDto {

    @XmlElement(name = "race-entry")
    private RaceEntrieImportDto[] raceEntrieImportDtos;

    public RaceEntrieImportRootDto() {
    }

    public RaceEntrieImportDto[] getRaceEntrieImportDtos() {
        return this.raceEntrieImportDtos;
    }

    public void setRaceEntrieImportDtos(RaceEntrieImportDto[] raceEntrieImportDtos) {
        this.raceEntrieImportDtos = raceEntrieImportDtos;
    }
}
