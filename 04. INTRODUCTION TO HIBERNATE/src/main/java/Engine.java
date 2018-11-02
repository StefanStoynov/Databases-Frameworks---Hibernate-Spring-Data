



import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;


public class Engine implements Runnable {
    private EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {
        this.removeObject();
    }

    /**
     * 2.	Remove Objects
     * Use the soft_uni database. Persist all towns from the database. Detach those whose name length is more than
     * 5 symbols. Then transform the names of all attached towns to lowercase and save them to the database.
     */
    private void removeObject(){
        this.entityManager.getTransaction().begin();
        List<Town> townsDetached = this.entityManager.createNativeQuery
                ("SELECT * FROM towns t WHERE char_length(t.name) > 5", Town.class).getResultList();
        townsDetached.forEach(t-> t.setName(t.getName().toLowerCase()));
        this.entityManager.getTransaction().commit();
    }

}

