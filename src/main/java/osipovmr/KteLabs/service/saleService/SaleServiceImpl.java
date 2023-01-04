package osipovmr.KteLabs.service.saleService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.exception.BadRequestException;
import osipovmr.KteLabs.model.dto.request.BuyRequest;
import osipovmr.KteLabs.model.dto.request.FinishCostRequest;
import osipovmr.KteLabs.model.dto.request.ProductValue;
import osipovmr.KteLabs.model.dto.request.StatisticRequest;
import osipovmr.KteLabs.model.dto.response.ProductFinishCostDto;
import osipovmr.KteLabs.model.dto.response.SaleDto;
import osipovmr.KteLabs.model.dto.response.StatisticDto;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.model.entity.Position;
import osipovmr.KteLabs.model.entity.Product;
import osipovmr.KteLabs.model.entity.Sale;
import osipovmr.KteLabs.repository.PersonRepository;
import osipovmr.KteLabs.repository.PositionRepository;
import osipovmr.KteLabs.repository.ProductRepository;
import osipovmr.KteLabs.repository.SaleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService{

    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final PositionRepository positionRepository;

    @Override
    public StatisticDto getStatistic(StatisticRequest dto) {
        StatisticDto statisticDto = new StatisticDto();
        Person person = personRepository.findPersonById(dto.getPersonId());
        List<Sale> personSales = saleRepository.findAllByPerson(person);

        statisticDto.setReceiptValue(personSales.size());

        Long cost = 0L;
        for (int i = 0; i < personSales.size(); i++) {
            cost = cost + personSales.get(i).getCost();
        }
        statisticDto.setCost(cost);

        Long discountSum = 0L;
        for (int i = 0; i < personSales.size(); i++) {
            Sale sale = personSales.get(i);
            List<Position> positions = sale.getPositions();
            for (int j = 0; j < positions.size(); j++) {
                discountSum = discountSum + (positions.get(j).getFinishCost() - positions.get(j).getStartCost());
            }
        }
        statisticDto.setDiscountSum(discountSum);

        return statisticDto;
    }

    @Override
    public SaleDto registerSale(BuyRequest dto) {
        Person person = personRepository.findPersonById(dto.getPersonId());
        SaleDto saleDto = new SaleDto();
        FinishCostRequest finishCostRequest = new FinishCostRequest();
        finishCostRequest.setPersonId(dto.getPersonId());
        finishCostRequest.setList(dto.getList());

        List<Position> list = getPositionList(finishCostRequest);

        Long finishCost = getFinishCost(finishCostRequest).getFinishCost();
        if (finishCost.equals(dto.getFinishCost())) {

            Sale sale = new Sale();
            sale.setPerson(person);
            sale.setSaleDate(LocalDateTime.now());
            sale.setReceiptNumber(getReceiptNumber());
            sale.setPositions(list);
            sale.setCost(finishCost);
            saleRepository.save(sale);

            return saleDto;
        }
        else throw new BadRequestException("ѕереданна€ итогова€ стоимость не соответствует рассчитанной на момент регистрации продажи");
    }

    @Override
    public ProductFinishCostDto getFinishCost(FinishCostRequest dto) {
        ProductFinishCostDto productFinishCostDto = new ProductFinishCostDto();
        List<Position> positionList = getPositionList(dto);
        Long totalCost = 0L;
        for (int i = 0; i < positionList.size(); i++) {
            totalCost = totalCost + positionList.get(i).getFinishCost();
        }
        productFinishCostDto.setFinishCost(totalCost);

        return productFinishCostDto;
    }

    private String getReceiptNumber() {
        String receiptNumber = "00100";
        List<Sale> todaySale = saleRepository.findAllBySaleDate(LocalDateTime.now());
        if (todaySale.isEmpty()) {
            return receiptNumber;
        }
        else {
            int number = 100 + todaySale.size();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("00");
            stringBuilder.append(number);
            return stringBuilder.toString();
        }
    }
    /**
     * ѕри заказе 5 и более единиц товара примен€етс€ индивидуальна€ скидка 2 (если не равна 0).
     * ѕри заказе меньшего числа единиц или отсутствии индивидуальной скидки 2 примен€етс€ индивидуальна€ скидка 1.
     * »ндивидуальна€ скидка суммируетс€ со скидкой на товар, но обща€ скидка не должна превышать 18%.
     */
    private List<Position> getPositionList (FinishCostRequest dto) {
        Person person = personRepository.findPersonById(dto.getPersonId());
        List<Position> positionList = new ArrayList<>();
        List<ProductValue> list = dto.getList();
        for (int i = 0; i < list.size(); i++) {
            ProductValue productValue = list.get(i);
            Position position = new Position();
            Product product = productRepository.findProductById(productValue.getProductId());
            position.setProduct(product);
            position.setValue(productValue.getValue());
            position.setStartCost(product.getPrice() * productValue.getValue());

            Long costFinish = 0L;
            Integer finishDiscount = 0;

            if ((productValue.getValue() > 4) && (nonNull(person.getSecondDiscount()))) {
                costFinish = (product.getPrice() * (1 - person.getSecondDiscount()/100)) * productValue.getValue();
                finishDiscount = person.getSecondDiscount();
                if (nonNull(product.getDiscount())) {
                    if (product.getDiscount() + person.getSecondDiscount() < 18) {
                        costFinish = (product.getPrice() * (1 - (person.getSecondDiscount() + product.getDiscount())/100)) * productValue.getValue();
                        finishDiscount = product.getDiscount() + person.getSecondDiscount();
                    }
                    else {
                        costFinish = Math.round((product.getPrice() * 0.82) * productValue.getValue());
                        finishDiscount = 18;
                    }
                }
            }
            else {
                costFinish = (product.getPrice() * (1 - person.getFirstDiscount()/100)) * productValue.getValue();
                finishDiscount = person.getFirstDiscount();
                if (nonNull(product.getDiscount())) {
                    if (product.getDiscount() + person.getFirstDiscount() < 18) {
                        costFinish = (product.getPrice() * (1 - (person.getFirstDiscount() + product.getDiscount())/100)) * productValue.getValue();
                        finishDiscount = product.getDiscount() + person.getFirstDiscount();
                    }
                    else {
                        costFinish = Math.round((product.getPrice() * 0.82) * productValue.getValue());
                        finishDiscount = 18;
                    }
                }
            }
            position.setFinishCost(costFinish);
            position.setFinishDiscount(finishDiscount);
            positionRepository.save(position);
            positionList.add(position);
        }
        return positionList;
    }
}
