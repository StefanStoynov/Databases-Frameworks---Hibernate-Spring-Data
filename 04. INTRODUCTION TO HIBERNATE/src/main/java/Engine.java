import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


@SuppressWarnings("ALL")
public class Engine implements Runnable {
    private EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {
        this.addressWithEmployeeCount();
    }

    /**
     * 2.	Remove Objects
     * Use the soft_uni database. Persist all towns from the database. Detach those whose name length is more than
     * 5 symbols. Then transform the names of all attached towns to lowercase and save them to the database.
     */

    private void removeObject() {
        this.entityManager.getTransaction().begin();
        List<Town> townsDetached = this.entityManager.createNativeQuery
                ("SELECT * FROM towns t WHERE char_length(t.name) > 5", Town.class).getResultList();
        townsDetached.forEach(t -> t.setName(t.getName().toLowerCase()));
        this.entityManager.getTransaction().commit();
    }

    /**
     * 3.	Contains Employee
     * Use the soft_uni database. Write a program that checks if a given employee name is contained in the database.
     */

    private void containsEmployee() throws IOException {
        Scanner scanner = new Scanner(System.in);

        String inputName = scanner.nextLine();

        this.entityManager.getTransaction().begin();
        try {
            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE concat(first_name, ' ', last_name) = :name", Employee.class)
                    .setParameter("name", inputName)
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException nre) {
            System.out.println("No");
        }
        this.entityManager.getTransaction().commit();
    }

    /**
     * 4.	Employees with Salary Over 50 000
     * Write a program that gets the first name of all employees who have salary over 50 000.
     */

    private void salaryOver() {
        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList();

        employees.forEach(employee -> System.out.println(employee.getFirstName()));

        this.entityManager.getTransaction().commit();
    }

    /**
     * 5.	Employees from Department
     * Extract all employees from the Research and Development department. Order them by salary (in ascending order),
     * then by id (in asc order). Print only their first name, last name, department name and salary.
     */

    private void employeesFromDepartment() {
        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager.createQuery("FROM Employee WHERE department_id = 6\n" +
                "ORDER BY salary, employee_id", Employee.class).getResultList();

        employees.forEach(e -> System.out.printf("%s %s from %s - $%.2f%n",
                e.getFirstName(),
                e.getLastName(),
                e.getDepartment().getName(),
                e.getSalary()));
        this.entityManager.getTransaction().commit();
    }

    /**
     * 6.	Adding a New Address and Updating Employee
     * Create a new address with text "Vitoshka 15".
     * Set that address to an employee with a last name, given as an input.
     */

    private void addNewAddressAndUpdateEmploee() {
        Scanner scanner = new Scanner(System.in);

        String employeeLastName = scanner.nextLine();

        this.entityManager.getTransaction().begin();

        String text = "Vitoshka 15";
        Town town = this.entityManager
                .createQuery("FROM Town WHERE name = 'Sofia'", Town.class)
                .getSingleResult();

        Address address = new Address();
        address.setText(text);
        address.setTown(town);

        this.entityManager.persist(address);

        Employee employee = this.entityManager
                .createQuery("FROM Employee WHERE last_name = :name", Employee.class)
                .setParameter("name",employeeLastName)
                .getSingleResult();

        this.entityManager.detach(employee.getAddress());
        employee.setAddress(address);
        this.entityManager.merge(employee);

        this.entityManager.getTransaction().commit();
    }

    /**
     * 7.	Addresses with Employee Count
     * Find all addresses, ordered by the number of employees who live there (descending), then by town id (ascending).
     * Take only the first 10 addresses and print their address text, town name and employee count.
     */

    private void addressWithEmployeeCount(){
        this.entityManager.getTransaction().begin();
        String query = "SELECT a.text, t.name, count(emp)" +
                "FROM Employee as emp " +
                "JOIN emp.address as a " +
                "JOIN a.town as t " +
                "GROUP BY a.text, t.name " +
                "ORDER BY count(emp) DESC ,t.id,a.id";

       this.entityManager.createQuery(query,Object[].class)
               .setMaxResults(10)
               .getResultList()
               .forEach(employee -> System.out.printf("%s %s - %d employees%n",employee[0],employee[1],employee[2]));








    }
}

