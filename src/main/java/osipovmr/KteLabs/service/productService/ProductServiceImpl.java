package osipovmr.KteLabs.service.productService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.response.ProductDto;
import osipovmr.KteLabs.model.dto.response.ProductExtraInfoDto;
import osipovmr.KteLabs.model.dto.request.ProductExtraInfoRequest;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.model.entity.Product;
import osipovmr.KteLabs.model.entity.Rating;
import osipovmr.KteLabs.repository.PersonRepository;
import osipovmr.KteLabs.repository.ProductRepository;
import osipovmr.KteLabs.repository.RatingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService   {

    private final ProductRepository productRepository;
    private final PersonRepository personRepository;
    private final RatingRepository ratingRepository;

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

        productExtraInfoDto.setProductDescription(product.getProductDescription());

        List<Rating> productRating = ratingRepository.findAllByProductId(dto.getProductId());
        int sumScore = productRating.stream().map(rating -> rating.getScore()).mapToInt(Integer::intValue).sum();
        int countRating = productRating.size();
        double averageScore = sumScore/countRating;
        String result = String.format("%.1f",averageScore);
        productExtraInfoDto.setAverageScore(result);
        productExtraInfoDto.setCurrentScore(ratingRepository.findRatingByPersonIdAndProductId(dto.getPersonId(), dto.getProductId()).getScore());

        HashMap<Integer, Long> map = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            map.put(i, ratingRepository.countAllByScore(i));
        }
        productExtraInfoDto.setMap(map);

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
}
