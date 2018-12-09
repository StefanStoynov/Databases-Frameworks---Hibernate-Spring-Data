package alararestaurant.domain.dtos.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImputRootDto {

    @XmlElement(name = "order")
    private OrderImputDto[]orderImputDtos;

    public OrderImputRootDto() {
    }

    public OrderImputDto[] getOrderImputDtos() {
        return this.orderImputDtos;
    }

    public void setOrderImputDtos(OrderImputDto[] orderImputDtos) {
        this.orderImputDtos = orderImputDtos;
    }
}
