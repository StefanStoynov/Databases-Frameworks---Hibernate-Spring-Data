package gamestore.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "games")
public class Game extends BaseEntity {

    private String title;
    private String trailer;
    private String imageThumbnail;
    private Double size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public Game() {
    }

    //<editor-fold desc="Getters">
    @Column(name = "title", nullable = false, unique = true)
    public String getTitle() {
        return this.title;
    }
    @Column(name = "trailer")
    public String getTrailer() {
        return this.trailer;
    }
    @Column(name = "image_thumbnail")
    public String getImage() {
        return this.imageThumbnail;
    }
    @Column(name = "size", nullable = false)
    public Double getSize() {
        return this.size;
    }
    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }
    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return this.description;
    }
    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }
    //</editor-fold>


    //<editor-fold desc="Setters">
    public void setTitle(String title) {
        this.title = title;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setImage(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    //</editor-fold>
}
