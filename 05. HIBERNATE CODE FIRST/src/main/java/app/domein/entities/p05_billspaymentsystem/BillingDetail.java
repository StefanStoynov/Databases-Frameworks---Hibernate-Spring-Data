package app.domein.entities.p05_billspaymentsystem;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail extends BaseEntity {
    private String number;
    private String owner;
    private User user;

    public BillingDetail() {
    }

    @Column(name = "number")
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "owner")
    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
