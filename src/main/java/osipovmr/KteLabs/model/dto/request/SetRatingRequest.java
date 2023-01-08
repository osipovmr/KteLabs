package osipovmr.KteLabs.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetRatingRequest {
    Integer personId;
	Integer productId;
	Integer score;
}
