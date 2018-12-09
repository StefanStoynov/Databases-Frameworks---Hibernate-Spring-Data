package alararestaurant.domain.dtos.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemImportRootDto {

    @XmlElement(name = "item")
    private ItemImportDto[] itemImportDtos;

    public ItemImportRootDto() {
    }

    public ItemImportDto[] getItemImportDtos() {
        return this.itemImportDtos;
    }

    public void setItemImportDtos(ItemImportDto[] itemImportDtos) {
        this.itemImportDtos = itemImportDtos;
    }
}
