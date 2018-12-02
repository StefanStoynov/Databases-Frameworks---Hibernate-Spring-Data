package app.ccb.services;

import app.ccb.domain.dtos.card.CardImportDto;
import app.ccb.domain.dtos.card.CardImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
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
public class CardServiceImpl implements CardService {
    private final static String CARD_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\11. WORKSHOP – MVC PROJECT SPRING MVC + SPRING DATA 1\\ColonialCouncilBank\\src\\main\\resources\\files\\xml\\cards.xml";

    private final CardRepository cardRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository, ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() throws IOException {
        return this.fileUtil.readFile(CARD_FILE_PATH);
    }

    @Override
    public String importCards() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        JAXBContext jaxbContext = JAXBContext.newInstance(CardImportRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CardImportRootDto cardEntity = (CardImportRootDto) unmarshaller.unmarshal(new File(CARD_FILE_PATH));

        for (CardImportDto dto : cardEntity.getCardImportDtos()) {
            BankAccount bankAccount = this.bankAccountRepository.getByAccountNumber(dto.getAccountNumber()).orElse(null);
            if (!this.validationUtil.isValid(dto) || bankAccount == null) {
                sb.append(sb.append("Error: Incorrect Data!").append(System.lineSeparator()));
                continue;
            }

            Card card = this.modelMapper.map(dto, Card.class);
            card.setBankAccount(bankAccount);

            this.cardRepository.saveAndFlush(card);

            sb.append(String.format("Successfully imported Card - %s.%n", card.getCardNumber()));

        }
        return sb.toString().trim();
    }
}
