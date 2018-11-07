package app.domein.entities.p02_salesdatabase;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "store_locations")
public class StoreLocation extends BaseClass {
    private String storeLocation;
    private Set<Sale> sales;

    public StoreLocation() {
    }

    @Column(name = "store_location")
    public String getStoreLocation() {
        return this.storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    @OneToMany(targetEntity = Sale.class, mappedBy = "storeLocation")
    public Set<Sale> getSales() {
        return this.sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
