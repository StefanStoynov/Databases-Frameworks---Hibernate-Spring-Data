package app.ccb.services;

import app.ccb.domain.dtos.EmployeeImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static String EMPLOYEE_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\11. WORKSHOP – MVC PROJECT SPRING MVC + SPRING DATA 1\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeesAreImported() {
       return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        String employeeFromJson = this.fileUtil.readFile(EMPLOYEE_FILE_PATH);
        return employeeFromJson;
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder sb = new StringBuilder();

        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);
        for (EmployeeImportDto dto : employeeImportDtos) {
            if (!this.validationUtil.isValid(dto)){
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Branch branchEntity = this.branchRepository.findByName(dto.getBranchName()).orElse(null);

            if (branchEntity == null){
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }
            Employee employeeEntity = this.modelMapper.map(dto, Employee.class);
            employeeEntity.setFirstName(dto.getFullName().split("\\s+")[0]);
            employeeEntity.setLastName(dto.getFullName().split("\\s+")[1]);
            employeeEntity.setStartedOn(LocalDate.parse(dto.getStartedOn()));
            employeeEntity.setBranch(branchEntity);

            this.employeeRepository.saveAndFlush(employeeEntity);
            sb.append(String.format("Successfully imported Employee - %s.%n",dto.getFullName()));
        }

        return sb.toString().trim();
    }

    @Override
    public String exportTopEmployees() {
        // TODO : Implement Me
        return null;
    }
}
