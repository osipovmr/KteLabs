package osipovmr.KteLabs.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuyRequest {
    Integer personId;
    List<ProductValue> list;    //парами: идентификатор товара, количество
    Long finishCost;    //итоговая стоимость с учетом скидок (в копейках)

}
