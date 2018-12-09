package alararestaurant.service;

import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/employees.json";

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final FileUtil fileUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, FileUtil fileUtil) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEE_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder employeesResult = new StringBuilder();

        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

        Arrays.stream(employeeImportDtos).forEach(employeeImportDto -> {

            if (!this.validationUtil.isValid(employeeImportDto)) {
                employeesResult.append("Invalid data format.").append(System.lineSeparator());
                return;
            }

            Position position = this.positionRepository.findByName(employeeImportDto.getPosition()).orElse(null);

            if (position == null) {
                position = new Position();
                position.setName(employeeImportDto.getPosition());

                if (!this.validationUtil.isValid(position) || position.getName() == null) {
                    employeesResult.append("Invalid data format.").append(System.lineSeparator());
                    return;
                }

                this.positionRepository.saveAndFlush(position);

            }

            Employee employee = this.modelMapper.map(employeeImportDto, Employee.class);

            employee.setPosition(position);
            this.employeeRepository.saveAndFlush(employee);

            employeesResult
                    .append(String.format("Record %s successfully imported."
                            ,employee.getName()))
                    .append(System.lineSeparator());

        });

        return employeesResult.toString().trim();
    }
}
