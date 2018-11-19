package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllTitleAndPriceWithBookBefore(LocalDate date);

    List<String> getAllBooksByAgeRestriction(String ageRestrictionString);

    List<String> getAllBooksByCopies();

    List<String> getAllBooksByPrice();

    List<String> getBooksNotInThisYear(LocalDate before, LocalDate after);

    List<String> getBooksContaining(String input);

    List<String> getBooksWrittenByAuthorsLastNameEndsWith(String input);

    int countOfBooksWithLongerThenGivenNumberTitle(int number);

    List<Object[]> getAuthorsNameAndCountOfBooks();

    List<String> getBookByTitle(String title);
}
