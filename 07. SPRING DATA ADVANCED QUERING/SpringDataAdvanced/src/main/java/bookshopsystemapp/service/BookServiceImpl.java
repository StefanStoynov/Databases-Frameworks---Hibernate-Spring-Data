package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.*;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks" +
            "---Hibernate-Spring-Data\\07. SPRING DATA ADVANCED QUERING\\SpringDataAdvanced\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllTitleAndPriceWithBookBefore(LocalDate date) {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(date);

        return books.stream().map(b -> String.format("Title:%s, Edition Type:%s, Price:%.2f",
                b.getTitle(),
                b.getEditionType(),
                b.getPrice())).collect(Collectors.toSet());
    }


    @Override
    public List<String> getAllBooksByAgeRestriction(String ageRestrictionString) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionString.toUpperCase());

        List<Book> books = this.bookRepository.findAllByAgeRestriction(ageRestriction);

        return books.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksByPrice() {
        BigDecimal lowerNumber = BigDecimal.valueOf(5);
        BigDecimal upperNumber = BigDecimal.valueOf(40);

        List<Book> books = this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerNumber, upperNumber);
        return books
                .stream()
                .map(book -> String.format("%s - $%.2f", book.getTitle(), book.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksNotInThisYear(LocalDate before, LocalDate after) {
        List<Book> books = this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(before, after);

        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }


    @Override
    public List<String> getAllBooksByCopies() {
        EditionType editionType = EditionType.valueOf("gold".toUpperCase());
        Integer treshholdForGoldenEdition = 5000;
        List<Book> books = this
                .bookRepository
                .findAllByEditionTypeAndCopiesLessThan(editionType, treshholdForGoldenEdition);

        return books.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksContaining(String input) {
        List<Book>books = this.bookRepository.findAllByTitleContains(input);

        return books.stream().map(b-> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksWrittenByAuthorsLastNameEndsWith(String input) {
        String wildCard = input+"%";
        List<Book> books = this.bookRepository.findAllByAuthorLastNameEndingWith(wildCard);

        return books
                .stream()
                .map(b->String.format("%s (%s %s)",b.getTitle(),b.getAuthor().getFirstName(),b.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public int countOfBooksWithLongerThenGivenNumberTitle(int number) {
        List<Book> allBooks = this.bookRepository.findAll();
        List<Book> booksWithCorrectLengthTitle = allBooks
                .stream()
                .filter(b-> b.getTitle().length()>= number)
                .collect(Collectors.toList());
        return booksWithCorrectLengthTitle.size();
    }

    @Override
    public List<Object[]> getAuthorsNameAndCountOfBooks() {

        return this.bookRepository.findAllByBookCopies();
    }

    @Override
    public List<String> getBookByTitle(String title) {
        List<Book> books = this.bookRepository.getBooksByTitle(title);

        return books.stream()
                .map(book -> String.format("%s %s %s %.2f",
                    book.getTitle(),
                    book.getEditionType(),
                    book.getAgeRestriction(),
                    book.getPrice()))
                .collect(Collectors.toList());
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }
}
