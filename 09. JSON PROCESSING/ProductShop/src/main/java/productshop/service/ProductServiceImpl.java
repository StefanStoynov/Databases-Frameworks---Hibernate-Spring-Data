package productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productshop.domain.dtos.ProductInRangeDto;
import productshop.domain.dtos.ProductSeedDto;
import productshop.domain.entities.Category;
import productshop.domain.entities.Product;
import productshop.domain.entities.User;
import productshop.repository.CategoryRepository;
import productshop.repository.ProductRepository;
import productshop.repository.UserRepository;
import productshop.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        for (ProductSeedDto productSeedDto : productSeedDtos) {
            if (!this.validatorUtil.isValid(productSeedDto)) {
                this.validatorUtil
                        .violations(productSeedDto)
                        .forEach(v -> System.out.println(v.getMessage()));
                continue;
            }

            Product entity = this.modelMapper.map(productSeedDto, Product.class);
            entity.setSeller(this.getRandomUser());

            Random random = new Random();
            if (random.nextInt() % 7 != 0) {
                entity.setBuyer(this.getRandomUser());
            }

            entity.setCategories(this.getRandomCategories());

            this.productRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<ProductInRangeDto> getProductsInRange(BigDecimal lower, BigDecimal higher) {
        List<Product> entities = this.productRepository.findAllByPriceBetweenAndBuyerOrderByPrice(lower, higher, null);

        List<ProductInRangeDto> productInRangeDtos = new ArrayList<>();

        for (Product entity : entities) {
            ProductInRangeDto productInRangeDto = this.modelMapper.map(entity, ProductInRangeDto.class);
            productInRangeDto.setSeller(String.format("%s %s",entity.getSeller().getFirstName(), entity.getSeller().getLastName()));
            productInRangeDtos.add(productInRangeDto);
        }
        return productInRangeDtos;
    }

    private User getRandomUser() {
        Random random = new Random();

        return this.userRepository.getOne(random.nextInt((int) this.userRepository.count() - 1) + 1);
    }

    private List<Category> getRandomCategories() {
        Random random = new Random();

        List<Category> categories = new ArrayList<>();

        int iterations = random.nextInt((int) this.categoryRepository.count() - 1) + 1;

        for (int i = 0; i < iterations; i++) {
            categories.add(this.categoryRepository.getOne(random.nextInt((int) this.categoryRepository.count() - 1) + 1));
        }

        return categories;
    }

}
