package osipovmr.KteLabs.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticDto {
    Integer receiptValue;   //кол-во чеков;
	Long cost;  //общая стоимость
	Long discountSum;   //сумма скидок
}
