package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Rating findRatingByPersonIdAndProductId(Integer personId, Integer productId);
    List<Rating> findAllByProductId(Integer productId);
    Integer countAllByProductIdAndScore(Integer productId, Integer score);
}
