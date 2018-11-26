package productshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productshop.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
