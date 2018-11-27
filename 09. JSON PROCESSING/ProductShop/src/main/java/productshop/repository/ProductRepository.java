package productshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productshop.domain.entities.Product;
import productshop.domain.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByPriceBetweenAndBuyerOrderByPrice(BigDecimal lower, BigDecimal higher, User user);
}
