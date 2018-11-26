package productshop.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "categories")
public class Category extends BaseEntity {

    private String name;
    private List<Product> products;

    public Category() {
    }

    //<editor-fold desc="Getters">
    public String getName() {
        return this.name;
    }

    @ManyToMany(targetEntity = Product.class, mappedBy = "categories")
    public List<Product> getProducts() {
        return this.products;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    //</editor-fold>
}
