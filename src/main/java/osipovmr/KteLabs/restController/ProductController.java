package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.model.dto.request.ProductExtraInfoRequest;
import osipovmr.KteLabs.service.productService.ProductService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Получение списка товаров
     * @return Идентификатор, наименование, цена.
     */
    @GetMapping("/findAllProduct")
    public ResponseEntity<?> findAllProduct(){
        return ResponseEntity.ok(productService.findAllProduct());
    }

    /**
     * Получение дополнительной информации о товаре
     * @param dto
     *     - идентификатор товара;
     *      - идентификатор клиента
     * @return
     *      - описание;
     *      - средняя оценка (с точностью до 1 десятичного знака);
     *      - распределение оценок (от 1 до 5, парами "оценка - количество");
     *      - текущая оценка товара клиентом.
     */
    @PostMapping("/getProductExtraInfo")
    public ResponseEntity<?> getProductExtraInfo(@RequestBody @Valid ProductExtraInfoRequest dto){
        return ResponseEntity.ok(productService.getProductExtraInfo(dto));
    }
}
