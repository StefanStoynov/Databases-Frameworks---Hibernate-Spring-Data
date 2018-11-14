package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author findByNames(String firstName, String lastName);

    List<Author> getAuthorsOrderByBookNumber();

}
