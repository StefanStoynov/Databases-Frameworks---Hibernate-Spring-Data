package app.domein.entities.p05_billspaymentsystem;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    private int id;

    public BaseEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
