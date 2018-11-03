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
        this.employeesFromDepartment();

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

    private void salaryOver(){
        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE salary > 50000",Employee.class)
                .getResultList();

        employees.forEach(employee -> System.out.println(employee.getFirstName()));

        this.entityManager.getTransaction().commit();
    }

    /**
     * 5.	Employees from Department
     * Extract all employees from the Research and Development department. Order them by salary (in ascending order),
     * then by id (in asc order). Print only their first name, last name, department name and salary.
     */

    private void employeesFromDepartment(){
        this.entityManager.getTransaction().begin();

       List<Employee> employees = this.entityManager.createQuery("FROM Employee WHERE department_id = 6\n" +
                "ORDER BY salary, employee_id",Employee.class).getResultList();

       employees.forEach(e -> System.out.printf("%s %s from %s - $%.2f%n",
               e.getFirstName(),
               e.getLastName(),
               e.getDepartment().getName(),
               e.getSalary()));
    }
}

