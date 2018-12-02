package app.ccb.services;

import app.ccb.domain.dtos.ClientImportDto;
import app.ccb.domain.entities.Card;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String CLIENT_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\11. WORKSHOP – MVC PROJECT SPRING MVC + SPRING DATA 1\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\clients.json";

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean clientsAreImported() {

        return this.clientRepository.count() != 0;

    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return this.fileUtil.readFile(CLIENT_FILE_PATH);
    }

    @Override
    public String importClients(String clients) {
        StringBuilder sb = new StringBuilder();

        ClientImportDto[] clientImportDtos = this.gson.fromJson(clients, ClientImportDto[].class);

        for (ClientImportDto dto : clientImportDtos) {
            if (!this.validationUtil.isValid(dto)) {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Employee employeeEntity = this.employeeRepository
                    .findByFirstNameAndLastName(
                            dto.getAppointedEmployeeFullName().split("\\s+")[0],
                            dto.getAppointedEmployeeFullName().split("\\s+")[1])
                    .orElse(null);

            if (employeeEntity == null) {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Client client = this.clientRepository.findByFullName(String.format("%s %s", dto.getFirstName(), dto.getLastName())).orElse(null);

            if (client != null) {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Client clientEntity = this.modelMapper.map(dto, Client.class);
            clientEntity.setFullName(String.format("%s %s", dto.getFirstName(), dto.getLastName()));
            clientEntity.getEmployees().add(employeeEntity);

            this.clientRepository.saveAndFlush(clientEntity);

            sb.append(String.format("Successfully imported Client - %s.%n", clientEntity.getFullName()));
        }


        return sb.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {
        List<Client> clients = this.clientRepository.clientWithMostCards();

        StringBuilder result = new StringBuilder();

        Client familyGay = clients.get(0);

        result.append(String.format("Full name: %s%nAge: %d%nBank account number: %s%n"
                , familyGay.getFullName()
                , familyGay.getAge()
                , familyGay.getBankAccount().getAccountNumber()
        ));
        result.append(" Cards:").append(System.lineSeparator());
        for (Card card : familyGay.getBankAccount().getCards()) {

            result.append(String.format("   %s - %s%n", card.getCardNumber(), card.getCardStatus()));
        }

        return result.toString().trim();
    }
}
