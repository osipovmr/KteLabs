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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final PositionRepository positionRepository;

    @Override
    public StatisticDto getStatistic(StatisticRequest dto) {
        StatisticDto statisticDto = new StatisticDto();
        Product product = productRepository.findProductById(dto.getProductId());
        if (isNull(product)) throw new BadRequestException("Product with id " + dto.getProductId() + " was not found.");
        Person person = personRepository.findPersonById(dto.getPersonId());
        if (isNull(person)) throw new BadRequestException("Person with id " + dto.getPersonId() + " was not found.");

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
                discountSum = discountSum + (positions.get(j).getStartCost() - positions.get(j).getFinishCost());
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        List<Position> list = getPositionList(person, dto.getList());

        Long finishCost = getFinishCost(finishCostRequest).getFinishCost();
        if (finishCost.equals(dto.getFinishCost())) {

            Sale sale = new Sale();
            sale.setPerson(person);
            sale.setSaleDate(formattedDateTime);
            sale.setReceiptNumber(getReceiptNumber());
            sale.setPositions(list);
            sale.setCost(finishCost);
            saleRepository.save(sale);

            saleDto.setReceiptNumber(sale.getReceiptNumber());

            return saleDto;
        } else
            throw new BadRequestException("Переданная итоговая стоимость не соответствует рассчитанной на момент регистрации продажи");
    }

    @Override
    public ProductFinishCostDto getFinishCost(FinishCostRequest dto) {
        ProductFinishCostDto productFinishCostDto = new ProductFinishCostDto();

        Person person = personRepository.findPersonById(dto.getPersonId());
        if (isNull(person)) throw new BadRequestException("Person with id " + dto.getPersonId() + " was not found.");

        List<Position> positionList = getPositionList(person, dto.getList());
        Long totalCost = 0L;
        for (int i = 0; i < positionList.size(); i++) {
            totalCost = totalCost + positionList.get(i).getFinishCost();
        }
        productFinishCostDto.setFinishCost(totalCost);

        return productFinishCostDto;
    }

    private String getReceiptNumber() {
        String receiptNumber = "00100";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.now();
        String today = dateTime.format(formatter);
        List<Sale> todaySale = saleRepository.findAllBySaleDate(today);
        if (todaySale.isEmpty()) {
            return receiptNumber;
        } else {
            int number = 100 + todaySale.size();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("00");
            stringBuilder.append(number);
            return stringBuilder.toString();
        }
    }

    /**
     * При заказе 5 и более единиц товара применяется индивидуальная скидка 2 (если не равна 0).
     * При заказе меньшего числа единиц или отсутствии индивидуальной скидки 2 применяется индивидуальная скидка 1.
     * Индивидуальная скидка суммируется со скидкой на товар, но общая скидка не должна превышать 18%.
     */
    private List<Position> getPositionList(Person person, List<ProductValue> list) {
        List<Position> positionList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ProductValue productValue = list.get(i);
            Position position = new Position();
            Product product = productRepository.findProductById(productValue.getProductId());
            position.setProduct(product);
            position.setValue(productValue.getValue());
            Long startCost = product.getPrice() * productValue.getValue();
            position.setStartCost(startCost);    //начальная стоимость без скидок

            double costFinish = 0L;
            Integer finishDiscount = 0;

            if (    //больше 4 единиц товароа и есть вторая персональная скидка
                    (productValue.getValue() > 4)
                            &&
                            (person.getSecondDiscount() > 0)
            ) {
                if ((nonNull(product.getDiscount())) && (product.getDiscount() != 0)) {   //есть скидка на товар
                    if (product.getDiscount() + person.getSecondDiscount() < 18) {  //суммарная скидка меньше 18%
                        finishDiscount = product.getDiscount() + person.getSecondDiscount();
                        System.out.println("1 case ");
                    } else {    //суммарная скидка больше 18%
                        finishDiscount = 18;
                        System.out.println("2 case");
                    }
                } else {  //нет скидки на товар
                    finishDiscount = person.getSecondDiscount();
                    System.out.println("3 case");
                }
            } else { //меньше 5 единиц товара или нет второй персональной скидки
                if ((nonNull(product.getDiscount())) && (product.getDiscount() != 0)) {   //есть скидка на товар
                    if (product.getDiscount() + person.getFirstDiscount() < 18) {  //суммарная скидка меньше 18%
                        finishDiscount = product.getDiscount() + person.getFirstDiscount();
                        System.out.println("4 case");
                    } else {    //суммарная скидка больше 18%
                        finishDiscount = 18;
                        System.out.println("5 case");
                    }
                } else {  //нет скидки на товар
                    finishDiscount = person.getFirstDiscount();
                    System.out.println("6 case");
                }
            }
            System.out.println(finishDiscount);
            double a = 1 - (finishDiscount * 1.0)/100;
            System.out.println(a);
            costFinish = startCost * a;
            System.out.println(costFinish);
            position.setFinishCost(Math.round(costFinish));
            position.setFinishDiscount(finishDiscount);
            positionRepository.save(position);
            positionList.add(position);
        }
        return positionList;
    }
}
