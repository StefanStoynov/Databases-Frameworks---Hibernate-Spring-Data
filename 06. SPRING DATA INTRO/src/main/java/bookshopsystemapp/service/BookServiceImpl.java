package bookshopsystemapp.service;

import bookshopsystemapp.Util.FileUtil;
import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.domain.entities.Book;
import bookshopsystemapp.domain.entities.Category;
import bookshopsystemapp.domain.entities.enumeration.EditionType;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---" +
            "Hibernate-Spring-Data\\06. SPRING DATA INTRO\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] books = this.fileUtil.getFileContent(BOOK_PATH);

        for (String line : books) {

            String[] bookData = line.split("\\s+");

            Book book = new Book();
            Author author = getRandomAuthor();
            book.setAuthor(author);
            EditionType editionType = EditionType.values()[Integer.parseInt(bookData[0])];
            book.setEditionType(editionType);
            LocalDate releaseDate = LocalDate.parse(bookData[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);


        }
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Category getRandomCategory(){
        Random random = new Random();
        int randomId = random.nextInt((int) ((this.categoryRepository.count()-1))+1);

        return this.categoryRepository.findById(randomId).orElse(null);
    }
}
