package carshop.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "suppliers")
public class Supplier extends BaseEntity {
    private String name;
    private boolean isImporter;

    public Supplier() {
    }

    //<editor-fold desc=
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "is_importer")
    public boolean isImporter() {
        return isImporter;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
    //</editor-fold>
}
