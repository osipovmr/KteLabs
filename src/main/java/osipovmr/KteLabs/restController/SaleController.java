package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.model.dto.request.BuyRequest;
import osipovmr.KteLabs.model.dto.request.FinishCostRequest;
import osipovmr.KteLabs.model.dto.request.StatisticRequest;
import osipovmr.KteLabs.service.saleService.SaleService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    /**
     * ������ �������� ���������
     * @param dto ��. ���������:
     * 	- ������������� �������
     * 	- ������: ������������� ������,  ����������.
     * @return �������� ��������� � ������ ������ (� ��������).
     */
    @PostMapping("/getFinishCost")
    public ResponseEntity<?> getFinishCost(@RequestBody @Valid FinishCostRequest dto) {
        return ResponseEntity.ok(saleService.getFinishCost(dto));
    }

    /**
     *  ����������� �������
     * @param dto ��. ���������:
     *  - ������������� ������� (��� ��������, ��� ��� ���������� ������ �������� �� �������������� �������,
     *            ��� ���������� SpringSecurity �������� ����� ����� ������)
     *  - ������: ������������� ������, ����������;
     *  - �������� ��������� � ������ ������ (� ��������).
     * @return ����� ����
     * ������ � ��� ������, ���� ���������� �������� ��������� �� ������������� ������������ �� ������ ����������� �������.
     */
    @PostMapping("/getFinishCost/buy")
    public ResponseEntity<?> registerSale(@RequestBody @Valid BuyRequest dto) {
        return ResponseEntity.ok(saleService.registerSale(dto));
    }

    /**
     * ��������� ����������
     *
     *
     * @param dto ��. ���������:
     *      - ������������� �������;
     *      - ������������� ������;
     *      ����� ���� ������� ������ ����.
     * @return ���. ���������:
     *      - ���-�� �����;
     *      - ����� ��������� (��� ������� - �����, ��� ������� - �����. �������) �� �������� ����;
     *      - ����� ������ (��� ������� - �� ���� �������� �����, ��� ������� - �����. �������).
     */
    @PostMapping("/getStatistic")
    public ResponseEntity<?> getStatistic(@RequestBody @Valid StatisticRequest dto) {
        return ResponseEntity.ok(saleService.getStatistic(dto));
    }
}
