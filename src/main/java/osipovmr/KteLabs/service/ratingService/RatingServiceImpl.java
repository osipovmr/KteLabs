package osipovmr.KteLabs.service.ratingService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.request.SetRatingRequest;
import osipovmr.KteLabs.model.entity.Rating;
import osipovmr.KteLabs.repository.RatingRepository;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;
    @Override
    public ResponseEntity<?> setRating(SetRatingRequest dto) {
        Rating rating = ratingRepository.findRatingByPersonIdAndProductId(dto.getPersonId(), dto.getProductId());
        if (isNull(dto.getScore())) {
            rating.setScore(null);
        }
        else {
            rating.setScore(dto.getScore());
        }
        ratingRepository.save(rating);
        return ResponseEntity.ok().build();
    }
}
