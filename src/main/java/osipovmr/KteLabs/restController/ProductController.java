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
     * @return ������ ������� (�������������, ������������, ����)
     */
    @GetMapping("/findAllProduct")
    public ResponseEntity<?> findAllProduct(){
        return ResponseEntity.ok(productService.findAllProduct());
    }
    /**
     * ��������� �������������� ���������� � ������
     *    ��. ���������:
     * - ������������� ������;
     * - ������������� �������
     *   ���. ���������:
     *    - ��������;
     *    - ������� ������ (� ��������� �� 1 ����������� �����);
     *    - ������������� ������ (�� 1 �� 5, ������ "������ - ����������");
     *    - ������� ������ ������ ��������.
     */
    @PostMapping("/getProductExtraInfo")
    public ResponseEntity<?> getProductExtraInfo(@Valid ProductExtraInfoRequest dto){
        return ResponseEntity.ok(productService.getProductExtraInfo(dto));
    }
}
