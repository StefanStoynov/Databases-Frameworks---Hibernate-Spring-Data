package app.domein.entities.p05_billspaymentsystem;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "credit_cards")
public class CreditCard extends BillingDetail {
    private String cardType;
    private int expirationMonth;
    private int expirationYear;

    public CreditCard() {
    }

    @Column(name = "card_type")
    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    @Column(name = "expiration_month")
    public int getExpirationMonth() {
        return this.expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }
    @Column(name = "expiration_year")
    public int getExpirationYear() {
        return this.expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
