package osipovmr.KteLabs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.SetRatingRequest;

@Service
public interface RatingService {
    ResponseEntity<?> setRating(SetRatingRequest dto);
}
