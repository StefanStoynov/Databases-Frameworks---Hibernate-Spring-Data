package app.ccb.services;

import app.ccb.domain.dtos.bankaccount.BankAccountDto;
import app.ccb.domain.dtos.bankaccount.BankAccountRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private static final String BANK_ACCOUNT_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\11. WORKSHOP – MVC PROJECT SPRING MVC + SPRING DATA 1\\ColonialCouncilBank\\src\\main\\resources\\files\\xml\\bank-accounts.xml";

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
    private final FileUtil fileUtil;


    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository, ValidationUtil validationUtil, ModelMapper mapper, FileUtil fileUtil) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean bankAccountsAreImported() {

     return this.bankAccountRepository.count() != 0;

    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        return this.fileUtil.readFile(BANK_ACCOUNT_FILE_PATH);
    }

    @Override
    public String importBankAccounts() throws JAXBException {

        StringBuilder sb = new StringBuilder();
        JAXBContext jaxbContext = JAXBContext.newInstance(BankAccountRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        BankAccountRootDto bankAccountEntity = (BankAccountRootDto) unmarshaller.unmarshal(new File(BANK_ACCOUNT_FILE_PATH));

        for (BankAccountDto bankAccount : bankAccountEntity.getBankAccounts()) {

            Client client = this.clientRepository.findByFullName(bankAccount.getClient()).orElse(null);

            if (!validationUtil.isValid(bankAccount)|| client == null){
                sb.append(sb.append("Error: Incorrect Data!").append(System.lineSeparator()));
                continue;
            }

            BankAccount bankEntity= this.mapper.map(bankAccount, BankAccount.class);
            bankEntity.setClient(client);
            this.bankAccountRepository.saveAndFlush(bankEntity);

            sb.append(String.format("Successfully imported Bank Account - %s.%n", bankEntity.getAccountNumber()));

        }

        return sb.toString().trim();
    }
}
