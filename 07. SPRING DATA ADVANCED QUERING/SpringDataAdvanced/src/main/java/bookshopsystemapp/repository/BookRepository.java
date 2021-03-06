package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.Book;
import bookshopsystemapp.domain.entities.EditionType;
import bookshopsystemapp.domain.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer numberOfCopies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal upper);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findAllByTitleContains(String input);

    @Query("SELECT b FROM bookshopsystemapp.domain.entities.Book as b JOIN b.author as a WHERE a.lastName like :wildCard")
    List<Book> findAllByAuthorLastNameEndingWith(@Param("wildCard") String wildCard);

    List<Book> findAll();

    @Query("SELECT a.firstName, a.lastName, b.copies FROM bookshopsystemapp.domain.entities.Book as b JOIN b.author as a group by a.id order by b.copies desc ")
    List<Object[]> findAllByBookCopies();

    List<Book> getBooksByTitle(String title);
}
