package productshop.service;

import productshop.domain.dtos.ProductSeedDto;

public interface ProductService {
    void seedProducts(ProductSeedDto[]productSeedDtos);
}
