package alararestaurant.domain.dtos.orders;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImputDto {

    @XmlElement(name = "customer")
    @NotNull
    private String customer;

    @XmlElement(name = "employee")
    @Length(min = 3 , max = 30)
    @NotNull
    private String employee;

    @XmlElement(name = "date-time")
    @NotNull
    private String dateTime;

    @Enumerated
    @XmlElement(name = "type")
    @NotNull
    private String type;

    @XmlElement(name = "items")
    private ItemImportRootDto items;

    public OrderImputDto() {
    }

    public String getCustomer() {
        return this.customer;
    }

    public String getEmployee() {
        return this.employee;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public String getType() {
        return this.type;
    }

    public ItemImportRootDto getItems() {
        return this.items;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setItems(ItemImportRootDto items) {
        this.items = items;
    }
}
