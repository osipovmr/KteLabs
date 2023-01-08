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
     * Получение статистики
     * @param dto
     *      - идентификатор клиента;
     *      - идентификатор товара;
     *      Может быть передан только один.
     * @return
     *     - кол-во чеков;
     *     - общая стоимость (для клиента - чеков, для товаров - соотв. позиций) по исходной цене;
     *     - сумма скидок (для клиента - по всем позициям чеков, для товаров - соотв. позиций).
     */
    @PostMapping("/getStatistic")
    public ResponseEntity<?> getStatistic(@RequestBody @Valid StatisticRequest dto) {
        return ResponseEntity.ok(statisticService.getStatistic(dto));
    }
}
