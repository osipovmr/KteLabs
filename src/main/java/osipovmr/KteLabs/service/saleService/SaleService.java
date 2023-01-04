package osipovmr.KteLabs.service.saleService;

import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.request.BuyRequest;
import osipovmr.KteLabs.model.dto.request.FinishCostRequest;
import osipovmr.KteLabs.model.dto.request.StatisticRequest;
import osipovmr.KteLabs.model.dto.response.ProductFinishCostDto;
import osipovmr.KteLabs.model.dto.response.SaleDto;
import osipovmr.KteLabs.model.dto.response.StatisticDto;

@Service
public interface SaleService {
    ProductFinishCostDto getFinishCost(FinishCostRequest dto);
    SaleDto registerSale(BuyRequest dto);
    StatisticDto getStatistic(StatisticRequest dto);
}
