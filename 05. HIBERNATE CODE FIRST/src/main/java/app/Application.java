package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("code_first");
        EntityManager em = entityManagerFactory.createEntityManager();

        Runnable engine = new Engine(em);

        engine.run();

    }
}
