package osipovmr.KteLabs.service.ratingService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.request.SetRatingRequest;

@Service
public interface RatingService {
    ResponseEntity<?> setRating(SetRatingRequest dto);
}
