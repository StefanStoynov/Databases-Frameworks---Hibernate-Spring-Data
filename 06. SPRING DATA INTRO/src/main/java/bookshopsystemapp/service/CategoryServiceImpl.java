package bookshopsystemapp.service;

import bookshopsystemapp.Util.FileUtil;
import bookshopsystemapp.domain.entities.Category;
import bookshopsystemapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---" +
            "Hibernate-Spring-Data\\06. SPRING DATA INTRO\\src\\main\\resources\\files\\categories.txt";
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] categories = this.fileUtil.getFileContent(CATEGORY_PATH);
        for (String line : categories) {
            Category category1 = new Category();
            category1.setName(line);

            this.categoryRepository.saveAndFlush(category1);
        }
    }
}
