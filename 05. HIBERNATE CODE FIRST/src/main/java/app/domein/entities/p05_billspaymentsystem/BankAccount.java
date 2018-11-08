package app.domein.entities.p05_billspaymentsystem;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "bank_accounts")
public class BankAccount extends BillingDetail {
    private String name;
    private String code;

    public BankAccount() {
    }
    @Column(name = "bank_name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "swift_code")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
