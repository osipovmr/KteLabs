package osipovmr.KteLabs.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDto {
    Integer personId;
    String personName;
    Integer firstDiscount;
    Integer secondDiscount;
}
