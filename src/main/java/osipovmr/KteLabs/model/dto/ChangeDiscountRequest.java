package osipovmr.KteLabs.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeDiscountRequest {
    Integer personId;
    Integer firstDiscount;
    Integer secondDiscount;
}
