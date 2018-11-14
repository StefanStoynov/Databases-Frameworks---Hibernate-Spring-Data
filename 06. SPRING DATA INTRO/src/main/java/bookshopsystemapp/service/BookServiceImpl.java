package bookshopsystemapp.service;

import bookshopsystemapp.Util.FileUtil;
import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.domain.entities.Book;
import bookshopsystemapp.domain.entities.Category;
import bookshopsystemapp.domain.entities.enumeration.AgeRestriction;
import bookshopsystemapp.domain.entities.enumeration.EditionType;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

            int copies = Integer.parseInt(bookData[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(bookData[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookData[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder titles = new StringBuilder();
            for (int i = 5; i <bookData.length ; i++) {
                titles.append(bookData[i]).append(" ");
            }
            titles.deleteCharAt(titles.lastIndexOf(" "));
            String titleBook = titles.toString();
            book.setTitle(titleBook);

            book.setCategories(this.getRandomCategories());

            this.bookRepository.saveAndFlush(book);

        }
    }

    @Override
    public List<String> getBooksAfter() {
        LocalDate localDate = LocalDate.parse("2000-12-31");

        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(localDate);

        return books.stream().map(book -> book.getTitle()).collect(Collectors.toList());
    }

    public Set<String> authorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream()
                .map(b->String.format("%s %s",b.getAuthor().getFirstName(),b.getAuthor().getLastName()))
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> booksFromAuthor() {
        Author author = this.authorRepository.findByFirstNameAndLastName("George","Powell");


        List<Book> books =this.bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author);
        return books.stream().map(b-> String.format("%s %s %d",b.getTitle(),b.getReleaseDate(),b.getCopies())).collect(Collectors.toList());
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

    private Set<Category> getRandomCategories(){
        Set<Category>randomCategories = new HashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();
            randomCategories.add(category);
        }

        return randomCategories;
    }
}
