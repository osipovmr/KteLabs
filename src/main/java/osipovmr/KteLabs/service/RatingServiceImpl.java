package osipovmr.KteLabs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.SetRatingRequest;
import osipovmr.KteLabs.model.entity.Rating;
import osipovmr.KteLabs.repository.RatingRepository;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;
    @Override
    public ResponseEntity<?> setRating(SetRatingRequest dto) {
        Rating rating = ratingRepository.findRatingByPersonIdAndProductId(dto.getPersonId(), dto.getProductId());

        if ((isNull(rating.getScore()))&&nonNull(dto.getScore())) {
            rating.setScore(dto.getScore());
            ratingRepository.save(rating);
        }
        else if (isNull(dto.getScore())) {
            rating.setScore(null);
        }
        return ResponseEntity.ok().build();
    }
}
