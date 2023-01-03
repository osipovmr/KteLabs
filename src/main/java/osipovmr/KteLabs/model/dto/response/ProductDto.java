package osipovmr.KteLabs.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Integer product_id;
    String productName;
    Long price;
}
