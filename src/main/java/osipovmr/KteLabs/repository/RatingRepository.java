package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.model.entity.Product;
import osipovmr.KteLabs.model.entity.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Rating findRatingByPersonAndProduct(Person person, Product product);
    List<Rating> findAllByProductId(Integer productId);
    Integer countAllByProductIdAndScore(Integer productId, Integer score);
}
