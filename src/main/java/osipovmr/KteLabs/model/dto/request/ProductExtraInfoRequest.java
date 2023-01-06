package osipovmr.KteLabs.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductExtraInfoRequest {
    Integer personId;   //идентификатор клиента
    Integer productId;  //идентификатор товара
}
