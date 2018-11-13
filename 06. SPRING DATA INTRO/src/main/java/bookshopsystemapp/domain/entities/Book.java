package bookshopsystemapp.domain.entities;

import bookshopsystemapp.domain.entities.enumeration.AgeRestriction;
import bookshopsystemapp.domain.entities.enumeration.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "books")
public class Book extends BaseEntity {
    private String title;
    private String description;
    private EditionType editionType;
    private BigDecimal price;
    private int copies;
    private LocalDate releaseDate;
    private AgeRestriction ageRestriction;
    private Author author;
    private Set<Category> categories;

    public Book() {
    }

    @Column(name = "title", length = 50, nullable = false)
    public String getTitle() {
        return this.title;
    }

    @Column(name = "description", columnDefinition = "text", length = 1000)
    public String getDescription() {
        return this.description;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    public EditionType getEditionType() {
        return this.editionType;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    @Column(name = "copies", nullable = false)
    public int getCopies() {
        return this.copies;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", nullable = false)
    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id")
    public Author getAuthor() {
        return this.author;
    }

    @ManyToMany()
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "books", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categories", referencedColumnName = "id")
    )
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
