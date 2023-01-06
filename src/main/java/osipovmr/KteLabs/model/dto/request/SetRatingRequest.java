package osipovmr.KteLabs.model.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetRatingRequest {
    Integer personId;	//идентификатор клиента;
	Integer productId;	//идентификатор товара;
	Integer score;	//оценка (1-5 или null для отзыва оценки)
}
