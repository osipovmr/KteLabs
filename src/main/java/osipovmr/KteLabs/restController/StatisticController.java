package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.model.dto.request.StatisticRequest;
import osipovmr.KteLabs.service.statisticService.StatisticService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

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
        return ResponseEntity.ok(statisticService.getStatistic(dto));
    }
}
