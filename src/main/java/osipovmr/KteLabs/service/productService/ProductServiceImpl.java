package osipovmr.KteLabs.service.productService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.request.FinishCostRequest;
import osipovmr.KteLabs.model.dto.request.ProductValue;
import osipovmr.KteLabs.model.dto.response.ProductDto;
import osipovmr.KteLabs.model.dto.response.ProductExtraInfoDto;
import osipovmr.KteLabs.model.dto.request.ProductExtraInfoRequest;
import osipovmr.KteLabs.model.dto.response.ProductFinishCostDto;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.model.entity.Product;
import osipovmr.KteLabs.model.entity.Rating;
import osipovmr.KteLabs.repository.PersonRepository;
import osipovmr.KteLabs.repository.ProductRepository;
import osipovmr.KteLabs.repository.RatingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

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

    /**
     * ѕри заказе 5 и более единиц товара примен€етс€ индивидуальна€ скидка 2 (если не равна 0).
     * ѕри заказе меньшего числа единиц или отсутствии индивидуальной скидки 2 примен€етс€ индивидуальна€ скидка 1.
     * »ндивидуальна€ скидка суммируетс€ со скидкой на товар, но обща€ скидка не должна превышать 18%.
     */
    @Override
    public ProductFinishCostDto getFinishCost(FinishCostRequest dto) {
        ProductFinishCostDto productFinishCostDto = new ProductFinishCostDto();
        Person person = personRepository.findPersonById(dto.getPersonId());
        Long costWithoutDiscount = 0L;
        Long costWithDiscount = 0L;
        Long totalValue = 0L;
        Long finishCost = 0L;
        List<ProductValue> list = dto.getList();
        for (int i = 0; i < list.size(); i++) {
            ProductValue productValue = list.get(i);
            Product product = productRepository.findProductById(productValue.getProductId());
            costWithoutDiscount = costWithoutDiscount + product.getPrice() * productValue.getValue();
            /**
             * повер€ем скидку на товар
             */
            if (nonNull(product.getDiscount()) && LocalDateTime.now().isBefore(product.getDiscountTimeTo()))
            {
                costWithDiscount = costWithDiscount + (product.getPrice() * (1 - (product.getDiscount() / 100)) * productValue.getValue());
            }
            else costWithDiscount = costWithDiscount + product.getPrice() * productValue.getValue();

            totalValue = totalValue + productValue.getValue();  //считаем, сколько единиц было заказано (всех товаров в сумме)
        }

        if ((totalValue > 4) && (nonNull(person.getSecondDiscount()))) {
            costWithDiscount = costWithDiscount * (1 -(person.getSecondDiscount() / 100 ));
        }
        else {
            costWithDiscount = costWithDiscount * (1 - (person.getFirstDiscount() / 100 ));
        }
        /**
         * проверка общей скидки
         */
        if (costWithoutDiscount / costWithDiscount > 1.18) {
            finishCost = Math.round(costWithoutDiscount * 0.82);
        }
        else finishCost = costWithDiscount;

        productFinishCostDto.setFinishCost(finishCost);

        return productFinishCostDto;
    }

    @Override
    public ProductExtraInfoDto getProductExtraInfo(ProductExtraInfoRequest dto) {
        ProductExtraInfoDto productExtraInfoDto = new ProductExtraInfoDto();
        Person person = personRepository.findPersonById(dto.getPersonId());
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
        productDto.setProduct_id(product.getProduct_id());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
