package productshop.web.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productshop.domain.dtos.CategorySeedDto;
import productshop.domain.dtos.ProductInRangeDto;
import productshop.domain.dtos.ProductSeedDto;
import productshop.domain.dtos.UserSeedDto;
import productshop.service.CategoryService;
import productshop.service.ProductService;
import productshop.service.UserService;
import productshop.util.FileIOUtil;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductShopController implements CommandLineRunner {

    private final String USER_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\09. JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\users.json";
    private final String CATEGORY_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\09. JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\categories.json";
    private final String PRODUCT_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\09. JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\products.json";

    private final FileIOUtil fileIOUtil;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final Gson gson;

    @Autowired
    public ProductShopController(FileIOUtil fileIOUtil, UserService userService, CategoryService categoryService, ProductService productService, Gson gson) {
        this.fileIOUtil = fileIOUtil;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedUsers();
        // this.seedCategories();
        // this.seedProducts();
        this.productsInRange();
    }

    private void seedUsers() throws IOException {
        String usersFileContent = this.fileIOUtil.readFile(USER_FILE_PATH);

        UserSeedDto[] userSeedDtos = this.gson.fromJson(usersFileContent, UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategories() throws IOException {
        String categoriesFileContent = this.fileIOUtil.readFile(CATEGORY_FILE_PATH);

        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(categoriesFileContent, CategorySeedDto[].class);

        this.categoryService.seedCategories(categorySeedDtos);
    }

    private void seedProducts() throws IOException {
        String productFileContent = this.fileIOUtil.readFile(PRODUCT_FILE_PATH);

        ProductSeedDto[] productSeedDtos = this.gson.fromJson(productFileContent, ProductSeedDto[].class);

        this.productService.seedProducts(productSeedDtos);
    }

    private void productsInRange() throws IOException {
        List<ProductInRangeDto> productInRangeDtos= this.productService.getProductsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String productInRangeJson = this.gson.toJson(productInRangeDtos);

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\09. JSON PROCESSING\\ProductShop\\src\\main\\resources\\output\\products-in-range.json"));

        writer.write(productInRangeJson);
        writer.close();
    }
}
