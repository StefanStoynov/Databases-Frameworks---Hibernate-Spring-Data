package mostwanted.domain.dtos.race;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportRootDto {

    @XmlElement(name = "entry")
    private EntryImportDto[]EntryImportDto;

    public EntryImportRootDto() {
    }

    public EntryImportDto[] getEntries() {
        return this.EntryImportDto;
    }

    public void setEntries(EntryImportDto[] entries) {
        this.EntryImportDto = entries;
    }
}
