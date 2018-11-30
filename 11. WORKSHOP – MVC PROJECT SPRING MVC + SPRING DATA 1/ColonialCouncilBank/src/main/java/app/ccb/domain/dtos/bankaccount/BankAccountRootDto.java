package app.ccb.domain.dtos.bankaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountRootDto {

    @XmlElement(name = "bank-account")
    private BankAccountDto[] bankAccounts;

    public BankAccountRootDto() {
    }

    public BankAccountDto[] getBankAccounts() {
        return this.bankAccounts;
    }

    public void setBankAccounts(BankAccountDto[] bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
