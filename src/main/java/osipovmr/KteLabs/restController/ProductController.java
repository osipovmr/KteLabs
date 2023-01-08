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
     * @return ������ ������� (�������������, ������������, ����)
     */
    @GetMapping("/findAllProduct")
    public ResponseEntity<?> findAllProduct(){
        return ResponseEntity.ok(productService.findAllProduct());
    }

    /**
     * ��������� �������������� ���������� � ������
     * @param dto ��. ���������:
     *      * - ������������� ������;
     *      * - ������������� �������
     * @return ���. ���������:
     *      *    - ��������;
     *      *    - ������� ������ (� ��������� �� 1 ����������� �����);
     *      *    - ������������� ������ (�� 1 �� 5, ������ "������ - ����������");
     *      *    - ������� ������ ������ ��������.
     */
    @PostMapping("/getProductExtraInfo")
    public ResponseEntity<?> getProductExtraInfo(@RequestBody @Valid ProductExtraInfoRequest dto){
        return ResponseEntity.ok(productService.getProductExtraInfo(dto));
    }
}
