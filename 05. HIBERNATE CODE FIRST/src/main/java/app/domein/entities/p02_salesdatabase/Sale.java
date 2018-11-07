package app.domein.entities.p02_salesdatabase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "sales")
public class Sale extends BaseClass {
    private Product product;
    private Customer customer;
    private StoreLocation storeLocation;
    private LocalDate date;

    public Sale() {
    }

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product", referencedColumnName = "id")
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer", referencedColumnName = "id")
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(targetEntity = StoreLocation.class)
    @JoinColumn(name = "storeLocation", referencedColumnName = "id")
    public StoreLocation getStoreLocation() {
        return this.storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    @Column(name = "date")
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
