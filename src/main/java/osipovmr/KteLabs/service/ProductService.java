package osipovmr.KteLabs.service;

import org.springframework.stereotype.Service;

import osipovmr.KteLabs.model.dto.ProductDto;
import osipovmr.KteLabs.model.dto.ProductExtraInfoDto;
import osipovmr.KteLabs.model.dto.ProductExtraInfoRequest;
import osipovmr.KteLabs.model.entity.Product;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> findAllProduct();
    ProductDto productDtoMapper(Product product);
    ProductExtraInfoDto getProductExtraInfo(ProductExtraInfoRequest dto);
}
