package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.model.dto.ProductExtraInfoRequest;
import osipovmr.KteLabs.service.ProductService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * @return список товаров (идентификатор, наименование, цена)
     */
    @GetMapping("/findAllProduct")
    public ResponseEntity<?> findAllProduct(){
        return ResponseEntity.ok(productService.findAllProduct());
    }
    /**
     * получение дополнительной информации о товаре
     *    вх. параметры:
     * - идентификатор товара;
     * - идентификатор клиента
     *   вых. параметры:
     *    - описание;
     *    - средн€€ оценка (с точностью до 1 дес€тичного знака);
     *    - распределение оценок (от 1 до 5, парами "оценка - количество");
     *    - текуща€ оценка товара клиентом.
     */
    @PostMapping("/getProductExtraInfo")
    public ResponseEntity<?> getProductExtraInfo(@Valid ProductExtraInfoRequest dto){
        return ResponseEntity.ok(productService.getProductExtraInfo(dto));
    }
}
