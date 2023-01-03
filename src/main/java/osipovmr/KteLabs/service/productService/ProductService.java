package osipovmr.KteLabs.service.productService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import osipovmr.KteLabs.model.dto.request.FinishCostRequest;
import osipovmr.KteLabs.model.dto.response.ProductDto;
import osipovmr.KteLabs.model.dto.response.ProductExtraInfoDto;
import osipovmr.KteLabs.model.dto.request.ProductExtraInfoRequest;
import osipovmr.KteLabs.model.dto.response.ProductFinishCostDto;
import osipovmr.KteLabs.model.entity.Product;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> findAllProduct();
    ProductDto productDtoMapper(Product product);
    ProductExtraInfoDto getProductExtraInfo(ProductExtraInfoRequest dto);
    ProductFinishCostDto getFinishCost(FinishCostRequest dto);
}
