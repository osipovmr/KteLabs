package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Product;
import osipovmr.KteLabs.model.entity.StatisticProduct;

public interface StatisticProductRepository extends JpaRepository<StatisticProduct, Integer> {
    StatisticProduct findStatisticProductByProduct(Product product);
}
