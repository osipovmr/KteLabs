package osipovmr.KteLabs.service.ratingService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.exception.BadRequestException;
import osipovmr.KteLabs.model.dto.request.SetRatingRequest;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.model.entity.Product;
import osipovmr.KteLabs.model.entity.Rating;
import osipovmr.KteLabs.repository.PersonRepository;
import osipovmr.KteLabs.repository.ProductRepository;
import osipovmr.KteLabs.repository.RatingRepository;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;
    private final PersonRepository personRepository;

    @Override
    public void setRating(SetRatingRequest dto) {
        Product product = productRepository.findProductById(dto.getProductId());
        if (isNull(product)) throw new BadRequestException("Product with id " + dto.getProductId() + " was not found.");
        Person person = personRepository.findPersonById(dto.getPersonId());
        if (isNull(person)) throw new BadRequestException("Person with id " + dto.getPersonId() + " was not found.");

        Rating rating = ratingRepository.findRatingByPersonAndProduct(person, product);

        if (isNull(rating)) {
            Rating newRating = new Rating();
            newRating.setPerson(person);
            newRating.setProduct(product);
            if (isNull(dto.getScore())) {
                newRating.setScore(null);
            } else if ((dto.getScore() > 0) && (dto.getScore() < 6)) {
                newRating.setScore(dto.getScore());
            } else throw new BadRequestException("Wrong score value.");
            ratingRepository.save(newRating);
        } else {
            if (isNull(dto.getScore())) {
                rating.setScore(null);
            } else if ((dto.getScore() > 0) && (dto.getScore() < 6)) {
                rating.setScore(dto.getScore());
            } else throw new BadRequestException("Wrong score value.");
            ratingRepository.save(rating);
        }
    }
}
