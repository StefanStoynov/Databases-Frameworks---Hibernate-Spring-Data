package app.ccb.services;

import app.ccb.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Boolean bankAccountsAreImported() {

     return this.bankAccountRepository.count() != 0;

    }

    @Override
    public String readBankAccountsXmlFile() {
        // TODO : Implement Me
        return null;
    }

    @Override
    public String importBankAccounts() {
        // TODO : Implement Me
        return null;
    }
}
