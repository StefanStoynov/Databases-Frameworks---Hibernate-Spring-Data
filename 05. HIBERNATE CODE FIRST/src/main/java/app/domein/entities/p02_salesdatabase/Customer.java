package app.domein.entities.p02_salesdatabase;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "customers")
public class Customer extends BaseClass {
    private String name;
    private String email;
    private String creditCardNumber;
    private Set<Sale> sales;

    public Customer() {
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    @Column(name = "credit_card_number")
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @OneToMany(targetEntity = Sale.class, mappedBy = "customer")
    public Set<Sale> getSales() {
        return this.sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
