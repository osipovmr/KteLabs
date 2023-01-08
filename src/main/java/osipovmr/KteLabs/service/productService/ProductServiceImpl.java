package osipovmr.KteLabs.service.productService;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.exception.BadRequestException;
import osipovmr.KteLabs.model.dto.response.ProductDto;
import osipovmr.KteLabs.model.dto.response.ProductExtraInfoDto;
import osipovmr.KteLabs.model.dto.request.ProductExtraInfoRequest;
import osipovmr.KteLabs.model.dto.response.ScoreValue;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.model.entity.Product;
import osipovmr.KteLabs.model.entity.Rating;
import osipovmr.KteLabs.repository.PersonRepository;
import osipovmr.KteLabs.repository.ProductRepository;
import osipovmr.KteLabs.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;
    private final PersonRepository personRepository;

    @Override
    public List<ProductDto> findAllProduct() {
        List<Product> findAllProduct = productRepository.findAll();
        List<ProductDto> allProductDto = findAllProduct.stream().map(this::productDtoMapper).collect(Collectors.toList());
        return allProductDto;
    }

    @Override
    public ProductExtraInfoDto getProductExtraInfo(ProductExtraInfoRequest dto) {
        ProductExtraInfoDto productExtraInfoDto = new ProductExtraInfoDto();
        Product product = productRepository.findProductById(dto.getProductId());
        if (isNull(product)) throw new BadRequestException("Product with id " + dto.getProductId() + " was not found.");
        Person person = personRepository.findPersonById(dto.getPersonId());
        if (isNull(person)) throw new BadRequestException("Person with id " + dto.getPersonId() + " was not found.");
        productExtraInfoDto.setProductDescription(product.getProductDescription());

        List<Rating> productRating = ratingRepository.findAllByProductId(dto.getProductId());
        double sumScore = 0;
        for (int i = 0; i < productRating.size(); i++) {
            sumScore = sumScore + productRating.get(i).getScore();

            System.out.println(sumScore);
        }
        if (sumScore == 0) productExtraInfoDto.setAverageScore(null);
        else {
            double countRating = productRating.size();
            double averageScore = sumScore / countRating;
            System.out.println(averageScore);
            String result = String.format("%.1f", averageScore);
            productExtraInfoDto.setAverageScore(result);
        }
        Rating personRating = ratingRepository.findRatingByPersonIdAndProductId(dto.getPersonId(), dto.getProductId());
        if ((!isNull(personRating)) && isNull(personRating.getScore())) {
            productExtraInfoDto.setCurrentPersonScore(personRating.getScore());
        } else productExtraInfoDto.setCurrentPersonScore(null);

        List<ScoreValue> list = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            ScoreValue scoreValue = new ScoreValue();
            scoreValue.setScore(i);
            scoreValue.setValue(ratingRepository.countAllByProductIdAndScore(dto.getProductId(), i));
            list.add(scoreValue);
        }
        productExtraInfoDto.setList(list);

        return productExtraInfoDto;
    }

    @Override
    public ProductDto productDtoMapper(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProduct_id(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    /**
     * Раз в час случайным образом выбирается товар, на который следующий час будет действовать скидка.
     * Скидка выбирается случайным образом от 5% до 10% и фиксируется в БД.
     */
    @Scheduled(cron = "@hourly")
    public void setProductDiscount() {
        List<Product> list = productRepository.findAll();
        Product product = list.stream().filter(p -> p.getDiscount() > 0).findFirst().orElse(null);
        if (nonNull(product)) {
            product.setDiscount(0);
            productRepository.save(product);
        }
        int count = list.size();

        Random rand = new Random();
        int randomProductId = rand.nextInt((count - 1) + 1) + 1;
        int randomDiscount = rand.nextInt((10 - 5) + 1) + 5;
        Product disProduct = productRepository.findProductById(randomProductId);
        disProduct.setDiscount(randomDiscount);
        productRepository.save(disProduct);
    }
}
