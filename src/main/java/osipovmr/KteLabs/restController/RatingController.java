package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.model.dto.request.SetRatingRequest;
import osipovmr.KteLabs.service.ratingService.RatingService;

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
    public ResponseEntity<Void> setRating(@RequestBody @Valid SetRatingRequest dto){
        ratingService.setRating(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
