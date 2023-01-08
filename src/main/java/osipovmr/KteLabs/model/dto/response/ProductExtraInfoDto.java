package osipovmr.KteLabs.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductExtraInfoDto {
    String productDescription;
    String averageScore;
    List<ScoreValue> list;
    Integer currentPersonScore;
}
