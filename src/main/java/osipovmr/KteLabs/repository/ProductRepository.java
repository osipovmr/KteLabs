package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    //Product findProductById(Integer productId);

    @Override
    Optional<Product> findById(Integer productId);
}
