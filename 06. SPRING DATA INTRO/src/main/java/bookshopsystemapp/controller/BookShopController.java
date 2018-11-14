package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BookShopController implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookShopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();

        this.getAuthorsWithBookAfter();
    }

    /**
     * 1.	Get all books after the year 2000. Print only their titles
     */
    private void getBooksTitles() {
        this.bookService.getBooksAfter().forEach(System.out::println);
    }

    /**
     * 2.	Get all authors with at least one book with release date before 1990.
     * Print their first name and last name.
     */
    private void getAuthorsWithBookAfter() {
        this.bookService.authorsWithBookBefore().forEach(System.out::println);
    }

    /**
     * 3.	Get all authors, ordered by the number of their books (descending).
     * Print their first name, last name and book count.
     */
    private void getAuthorsByBookNumber() {
        this.authorService.getAuthorsOrderByBookNumber()
                .forEach(x -> System.out.printf("%s %s %s%n",
                        x.getFirstName(),
                        x.getFirstName(),
                        x.getBooks().size()));
    }

    /**
     * 4.	Get all books from author George Powell, ordered by their release date (descending),
     * then by book title (ascending). Print the book's title, release date and copies.
     */
    private void getBooksByAuthor() {
        this.bookService.booksFromAuthor().forEach(System.out::println);
    }
}
