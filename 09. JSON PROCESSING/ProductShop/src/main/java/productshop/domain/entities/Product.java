package productshop.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "products")
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private User seller;
    private User buyer;
    private List<Category> categories;

    public Product() {
    }

    //<editor-fold desc="Getters">
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public User getSeller() {
        return this.seller;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getBuyer() {
        return this.buyer;
    }

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(name = "category_products",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    public List<Category> getCategories() {
        return this.categories;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    //</editor-fold>
}
