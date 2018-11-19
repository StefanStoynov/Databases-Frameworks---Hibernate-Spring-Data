package bookshopsystemapp.domain.models;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.EditionType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
public class BookModel {
    private String name;
    private EditionType editionType;
    private AgeRestriction ageRestriction;
    private BigDecimal price;

    public BookModel() {
    }

    public String getName() {
        return this.name;
    }

    public EditionType getEditionType() {
        return this.editionType;
    }

    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
