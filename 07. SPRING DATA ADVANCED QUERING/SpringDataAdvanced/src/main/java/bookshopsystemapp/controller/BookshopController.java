package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

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
        // this.authorService.seedAuthors();
        //  this.categoryService.seedCategories();
        //  this.bookService.seedBooks();
        /*
        1.	Books Titles by Age Restriction
        Write a program that prints the titles of all books, for which the age restriction
        matches the given input (minor, teen or adult). Ignore casing of the input.
         */

        // this.bookService.getAllBooksByAgeRestriction(this.scanner.nextLine()).stream().forEach(b -> System.out.println(b));

        /*
        2.	Golden Books
        Write a program that prints the titles of the golden edition books, which have less than 5000 copies.
         */

        // this.bookService.getAllBooksByCopies().stream().forEach(b-> System.out.println(b));

        /*
        3.	Books by Price
        Write a program that prints the titles and prices of books with price lower than 5 and higher than 40.
         */

        this.bookService.getAllBooksByPrice().forEach(System.out::println);

    }
}
