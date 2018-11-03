import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@SuppressWarnings("ALL")
public class Engine implements Runnable {
    private EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {

        try {
            this.containsEmployee();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputName = reader.readLine();

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

}

