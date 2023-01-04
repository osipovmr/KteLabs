package osipovmr.KteLabs.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductExtraInfoDto {
    String productDescription;  //описание
    String averageScore;   //средняя оценка (с точностью до 1 десятичного знака)
    List<ScoreValue> list;  //распределение оценок (от 1 до 5, парами "оценка - количество");
    Integer currentScore;  //текущая оценка товара клиентом
}
