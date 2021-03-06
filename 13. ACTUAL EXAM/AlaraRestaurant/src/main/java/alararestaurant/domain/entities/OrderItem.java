package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity(name = "order_items")
public class OrderItem extends BaseEntity{

    private Order order;
    private Item item;
    private Integer quantity;

    public OrderItem() {
    }

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    public Order getOrder() {
        return this.order;
    }

    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", referencedColumnName = "id" )
    public Item getItem() {
        return this.item;
    }

    @Column(name = "quantity", nullable = false)
    @Min(value = 1)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
