package osipovmr.KteLabs.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductExtraInfoDto {
    String productDescription;  //��������
    String averageScore;   //������� ������ (� ��������� �� 1 ����������� �����)
    List<ScoreValue> list;  //������������� ������ (�� 1 �� 5, ������ "������ - ����������");
    Integer currentScore;  //������� ������ ������ ��������
}
