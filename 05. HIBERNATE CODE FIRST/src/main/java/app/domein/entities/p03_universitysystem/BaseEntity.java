package app.domein.entities.p03_universitysystem;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    private int id;

    public BaseEntity() {
    }
    @Id
    @GeneratedValue()
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
