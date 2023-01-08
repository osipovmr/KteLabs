package osipovmr.KteLabs.service.statisticService;

import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.request.StatisticRequest;
import osipovmr.KteLabs.model.dto.response.StatisticDto;

@Service
public interface StatisticService {
    StatisticDto getStatistic(StatisticRequest dto);
}
