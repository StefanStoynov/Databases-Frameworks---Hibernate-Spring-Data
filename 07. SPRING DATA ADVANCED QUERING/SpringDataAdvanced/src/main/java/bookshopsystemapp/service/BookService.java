package bookshopsystemapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<String> getAllBooksByAgeRestriction(String ageRestrictionString);

    List<String> getAllBooksByCopies();

    List<String> getAllBooksByPrice();
}
