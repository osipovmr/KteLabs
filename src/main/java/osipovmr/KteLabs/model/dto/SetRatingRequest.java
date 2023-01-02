package osipovmr.KteLabs.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetRatingRequest {
    Integer personId;	//������������� �������;
	Integer productId;	//������������� ������;
	Integer score;	//������ (1-5 ��� null ��� ������ ������)
}
