package osipovmr.KteLabs.service.statisticService;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.exception.BadRequestException;
import osipovmr.KteLabs.model.dto.request.StatisticRequest;
import osipovmr.KteLabs.model.dto.response.StatisticDto;
import osipovmr.KteLabs.model.entity.*;
import osipovmr.KteLabs.repository.*;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final PositionRepository positionRepository;
    private final StatisticProductRepository statisticProductRepository;
    private final StatisticPersonRepository statisticPersonRepository;

    @Override
    public StatisticDto getStatistic(StatisticRequest dto) {
        StatisticDto statisticDto = new StatisticDto();
        if ((nonNull(dto.getProductId())) && (nonNull(dto.getPersonId()))) {
            throw new BadRequestException("Need only one parameter.");
        } else if (nonNull(dto.getProductId())) {
            Product product = productRepository.findProductById(dto.getProductId());
            if (isNull(product))
                throw new BadRequestException("Product with id " + dto.getProductId() + " was not found.");
            StatisticProduct statisticProduct = statisticProductRepository.findStatisticProductByProduct(product);
            statisticDto.setReceiptValue(statisticProduct.getReceiptValue());
            statisticDto.setCost(statisticProduct.getCost());
            statisticDto.setDiscountSum(statisticProduct.getDiscountSum());
        } else if (nonNull(dto.getPersonId())) {
            Person person = personRepository.findPersonById(dto.getPersonId());
            if (isNull(person))
                throw new BadRequestException("Person with id " + dto.getPersonId() + " was not found.");
            StatisticPerson statisticPerson = statisticPersonRepository.findStatisticPersonByPerson(person);
            statisticDto.setReceiptValue(statisticPerson.getReceiptValue());
            statisticDto.setCost(statisticPerson.getCost());
            statisticDto.setDiscountSum(statisticPerson.getDiscountSum());
        }
        return statisticDto;
    }

    @Scheduled(cron = "@hourly")
    public void setProductStatistic() {
        statisticProductRepository.deleteAll();
        List<Product> productList = productRepository.findAll();

        for (int i = 0; i < productList.size(); i++) {
            StatisticProduct statisticProduct = new StatisticProduct();
            Product product = productList.get(i);
            statisticProduct.setProduct(product);

            List<Position> positions = positionRepository.findAllByProduct(product);
            System.out.println(positions.size());

            Long cost = 0L;
            Long discount = 0L;
            for (int j = 0; j < positions.size(); j++) {
                cost = cost + positions.get(j).getFinishCost();
                discount = discount + (positions.get(j).getStartCost() - positions.get(j).getFinishCost());
            }

            statisticProduct.setReceiptValue(positions.size());
            statisticProduct.setCost(cost);
            statisticProduct.setDiscountSum(discount);

            statisticProductRepository.save(statisticProduct);
        }
    }

    @Scheduled(cron = "@hourly")
    public void setPersonStatistic() {
        statisticPersonRepository.deleteAll();
        List<Person> personList = personRepository.findAll();
        for (int i = 0; i < personList.size(); i++) {
            StatisticPerson statisticPerson = new StatisticPerson();
            Person person = personList.get(i);
            statisticPerson.setPerson(person);

            List<Sale> sales = saleRepository.findAllByPerson(person);
            statisticPerson.setReceiptValue(sales.size());

            Long cost = 0L;
            Long discount = 0L;
            for (int j = 0; j < sales.size(); j++) {
                cost = cost + sales.get(j).getCost();
                List<Position> positions = sales.get(j).getPositions();
                for (int k = 0; k < positions.size(); k++) {
                    discount = discount + (positions.get(k).getStartCost() - positions.get(k).getFinishCost());
                }
            }
            statisticPerson.setCost(cost);
            statisticPerson.setDiscountSum(discount);

            statisticPersonRepository.save(statisticPerson);
        }
    }
}
