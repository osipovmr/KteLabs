package osipovmr.KteLabs.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import osipovmr.KteLabs.model.dto.request.BuyRequest;
import osipovmr.KteLabs.model.dto.request.FinishCostRequest;
import osipovmr.KteLabs.service.saleService.SaleService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    /**
     *
     * @param dto вх. параметры:
     * 	- идентификатор клиента
     * 	- парами: идентификатор товара,  количество.
     * @return итоговая стоимость с учетом скидок (в копейках).
     */
    @PostMapping("/getFinishCost")
    public ResponseEntity<?> getFinishCost(@Valid FinishCostRequest dto) {
        return ResponseEntity.ok(saleService.getFinishCost(dto));
    }

    /**
     *  Регистрация продажи
     * @param dto вх. параметры:
     *  - идентификатор клиента (ввёл параметр, так как необходимо делать проверку по индивидуальным скидкам,
     *            при дополнении SpringSecurity параметр можно будет убрать)
     *  - парами: идентификатор товара, количество;
     *  - итоговая стоимость с учетом скидок (в копейках).
     * @return номер чека
     * Ошибка в том случае, если переданная итоговая стоимость не соответствует рассчитанной на момент регистрации продажи.
     */
    @PostMapping("/getFinishCost/buy")
    public ResponseEntity<?> registerSale(@Valid BuyRequest dto) {
        return ResponseEntity.ok(saleService.registerSale(dto));
    }
}
