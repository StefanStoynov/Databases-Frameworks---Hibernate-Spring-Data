package app.ccb.domain.dtos.bankaccount;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountDto {

    @XmlAttribute(name = "client")
    private String client;

    @XmlElement(name = "account-number")
    private String accountNumber;

    @XmlElement(name = "balance")
    private BigDecimal balance;

    public BankAccountDto() {
    }

    public String getClient() {
        return this.client;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
