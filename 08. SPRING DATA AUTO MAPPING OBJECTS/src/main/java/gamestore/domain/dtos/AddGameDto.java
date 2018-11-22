package gamestore.domain.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGameDto {
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    //<editor-fold desc="Constructors">
    public AddGameDto() {
    }

    public AddGameDto(String title, BigDecimal price, Double size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    //</editor-fold>

    //<editor-fold desc="Getters">
    @NotNull
    @Pattern(regexp = "[A-Z].{2,100}")
    public String getTitle() {
        return this.title;
    }

    @Pattern(regexp = "^.{11}$")
    public String getTrailer() {
        return this.trailer;
    }

    @Pattern(regexp = "(http(s)?:\\/\\/)?[^ ]*")
    public String getImageThumbnail() {
        return this.imageThumbnail;
    }

    @NotNull
    @Positive
    @Digits(integer = 19, fraction = 1)
    public Double getSize() {
        return this.size;
    }

    @NotNull
    @Positive
    @Digits(integer = 19, fraction = 2)
    public BigDecimal getPrice() {
        return this.price;
    }

    @Length(min = 20)
    public String getDescription() {
        return this.description;
    }

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

    public void setImageThumbnail(String imageThumbnail) {
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
