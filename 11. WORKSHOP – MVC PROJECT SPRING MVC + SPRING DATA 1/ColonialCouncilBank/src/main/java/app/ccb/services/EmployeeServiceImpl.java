package app.ccb.services;

import app.ccb.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Boolean employeesAreImported() {

       return this.employeeRepository.count() != 0;

    }

    @Override
    public String readEmployeesJsonFile() {
        // TODO : Implement Me
        return null;
    }

    @Override
    public String importEmployees(String employees) {
        // TODO : Implement Me
        return null;
    }

    @Override
    public String exportTopEmployees() {
        // TODO : Implement Me
        return null;
    }
}
