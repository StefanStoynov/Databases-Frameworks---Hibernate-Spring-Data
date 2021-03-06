package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
    private final Scanner scanner;


    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService, Scanner scanner) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... strings) throws Exception {
        //this.authorService.seedAuthors();
        //this.categoryService.seedCategories();
        //this.bookService.seedBooks();

        this.bookByTitle();

    }

    /**
     * 1.	Books Titles by Age Restriction
     * Write a program that prints the titles of all books, for which the age restriction
     * matches the given input (minor, teen or adult). Ignore casing of the input.
     */
    private void bookTitlesByAgeRestriction() {
        this.bookService.getAllBooksByAgeRestriction(this.scanner.nextLine())
                .forEach(System.out::println);
    }

    /**
     * 2.	Golden Books
     * Write a program that prints the titles of the golden edition books,
     * which have less than 5000 copies.
     */
    private void goldenBooks() {
        this.bookService.getAllBooksByCopies()
                .forEach(System.out::println);
    }

    /**
     * 3.	Books by Price
     * Write a program that prints the titles and prices of books with price lower than 5 and higher than 40.
     */
    private void booksByPrice() {
        this.bookService.getAllBooksByPrice()
                .forEach(System.out::println);
    }

    /**
     * 4.	Not Released Books
     * Write a program that prints the titles of all books that are NOT released in a given year.
     */
    private void notReleasedBooks(){
        String year = this.scanner.nextLine();
        LocalDate before = LocalDate.parse(year+"-01-01");
        LocalDate after = LocalDate.parse(year+"-12-31");
        this.bookService.getBooksNotInThisYear(before,after).forEach(System.out::println);
    }

    /**
     * 5.	Books Released Before Date
     * Write a program that prints the title, the edition type and the price of books, which are released before a
     * given date. The date will be in the format dd-MM-yyyy.
     */
    private void booksReleasedBeforeDate(){
        String date = this.scanner.nextLine();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date,formattedDate);
        this.bookService.getAllTitleAndPriceWithBookBefore(localDate).forEach(System.out::println);
    }

    /**
     * 6.	Authors Search
     * Write a program that prints the names of those authors, whose first name ends with a given string.
     */
    private void authorsSearch(){
        String firstNameEnd = this.scanner.nextLine();
        this.authorService.getByEndWithFirstName(firstNameEnd).forEach(System.out::println);
    }

    /**
     * 7.	Books Search
     * Write a program that prints the titles of books, which contain a given string (regardless of the casing).
     */
    private void bookSearch(){
        this.bookService.getBooksContaining(this.scanner.nextLine()).forEach(System.out::println);
    }

    /**
     * 8.	Book Titles Search
     * Write a program that prints the titles of books, which are written by authors,
     * whose last name starts with a given string.
     */
    private void bookTitlesSearch(){
        this.bookService
                .getBooksWrittenByAuthorsLastNameEndsWith(this.scanner.nextLine())
                .forEach(System.out::println);
    }

    /**
     * 9.	Count Books
     * Write a program that prints the number of books, whose title is longer or equal than a given number.
     */

    private void countBooks(){
        int number = Integer.parseInt(this.scanner.nextLine());
        System.out.println(this.bookService.countOfBooksWithLongerThenGivenNumberTitle(number));
    }

    /**
     * 10.	Total Book Copies
     * Write a program that prints the total number of book copies by author.
     * Order the results descending by total book copies.
     */

    private void totalBookCopies(){
        this.bookService.getAuthorsNameAndCountOfBooks().forEach(b-> System.out.printf("%s %s %s%n",b[0],b[1],b[2]));
    }

    /**
     * 11.	Reduced Book
     * Write a program that prints information (title, edition type, age restriction and price) for a book by given
     * title. When retrieving the book information select only those fields and do NOT include any other
     * information in the returned result.
     */

    private void bookByTitle(){
        String title = this.scanner.nextLine();

        this.bookService.getBookByTitle(title).forEach(System.out::println);
    }
}

