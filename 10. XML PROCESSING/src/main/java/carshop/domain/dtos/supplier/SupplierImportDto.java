package carshop.domain.dtos.supplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SupplierImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public boolean isImporter() {
        return this.isImporter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
