package osipovmr.KteLabs.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductExtraInfoRequest {
    Integer productId;  //идентификатор товара
    Integer personId;   //идентификатор клиента
}
