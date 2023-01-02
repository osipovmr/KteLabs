package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer productId);
}
