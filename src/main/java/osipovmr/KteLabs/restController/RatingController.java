package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.model.dto.ProductExtraInfoRequest;
import osipovmr.KteLabs.model.dto.SetRatingRequest;
import osipovmr.KteLabs.service.RatingService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    /**
     * Оценка товара
     * 	вх. параметры:
     * 	- идентификатор клиента;
     * 	- идентификатор товара;
     * 	- оценка (1-5 или null для отзыва оценки).
     */
    @PostMapping("/setRating")
    public ResponseEntity<?> setRating(@Valid SetRatingRequest dto){
        return ResponseEntity.ok(ratingService.setRating(dto));
    }
}
