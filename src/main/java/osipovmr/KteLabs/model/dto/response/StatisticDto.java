package osipovmr.KteLabs.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticDto {
    Integer receiptValue;   //кол-во чеков;
	Long cost;  //общая стоимость (для клиента - чеков, для товаров - соотв. позиций) по исходной цене;
	Long discountSum;   //сумма скидок (для клиента - по всем позициям чеков, для товаров - соотв. позиций).

    //не совсем понял, что значит разделение в скобках
}
