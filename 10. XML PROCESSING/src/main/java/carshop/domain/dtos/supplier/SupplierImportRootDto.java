package carshop.domain.dtos.supplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportRootDto {

    @XmlElement(name = "supplier")
    private SupplierImportDto[] supplierImportDtos;

    public SupplierImportRootDto() {
    }

    public SupplierImportDto[] getSupplierImportDtos() {
        return this.supplierImportDtos;
    }

    public void setSupplierImportDtos(SupplierImportDto[] supplierImportDtos) {
        this.supplierImportDtos = supplierImportDtos;
    }
}
