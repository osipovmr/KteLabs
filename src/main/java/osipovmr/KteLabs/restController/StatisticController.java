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
     * ѕолучение статистики
     *
     *
     * @param dto вх. параметры:
     *      - идентификатор клиента;
     *      - идентификатор товара;
     *      ћожет быть передан только один.
     * @return вых. параметры:
     *      - кол-во чеков;
     *      - обща€ стоимость (дл€ клиента - чеков, дл€ товаров - соотв. позиций) по исходной цене;
     *      - сумма скидок (дл€ клиента - по всем позици€м чеков, дл€ товаров - соотв. позиций).
     */
    @PostMapping("/getStatistic")
    public ResponseEntity<?> getStatistic(@RequestBody @Valid StatisticRequest dto) {
        return ResponseEntity.ok(statisticService.getStatistic(dto));
    }
}
