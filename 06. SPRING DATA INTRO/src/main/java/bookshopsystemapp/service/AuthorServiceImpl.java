package bookshopsystemapp.service;

import bookshopsystemapp.Util.FileUtil;
import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks" +
            "---Hibernate-Spring-Data\\06. SPRING DATA INTRO\\src\\main\\resources\\files\\authors.txt";
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_PATH);

        for (String line : authorFileContent) {

            String[] names = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public Author findByNames(String firstName, String lastName) {
        return this.authorRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<Author> getAuthorsOrderByBookNumber() {

        return authorRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(author -> -author.getBooks().size()))
                .collect(Collectors.toList());
    }
}
