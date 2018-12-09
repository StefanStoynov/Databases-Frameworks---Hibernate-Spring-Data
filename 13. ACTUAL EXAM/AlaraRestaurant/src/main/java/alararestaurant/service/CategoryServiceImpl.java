package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_FILE_PATH =  System.getProperty("user.dir") + "/src/main/resources/files/items.json";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder sb = new StringBuilder();

        List<Category>categories = this.categoryRepository.categoriesByCountOfItems();

        categories.stream().forEach(category -> {

            sb.append(String.format("Category: %s",category.getName())).append(System.lineSeparator());

            category.getItems().stream().forEach(item -> {

                sb.append(String.format("--- Item Name: %s", item.getName())).append(System.lineSeparator());
                sb.append(String.format("--- Item Price: %.2f", item.getPrice()));
                sb.append(System.lineSeparator());
                sb.append(System.lineSeparator());
            });

            sb.append(System.lineSeparator());

        });

        return sb.toString().trim();
    }
}
