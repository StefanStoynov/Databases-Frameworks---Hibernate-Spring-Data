package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

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

        this.authorsSearch();

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
}

