package osipovmr.KteLabs.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductExtraInfoDto {
    String productDescription;  //��������
    String averageScore;   //������� ������ (� ��������� �� 1 ����������� �����)
    HashMap<Integer, Long> map;  //������������� ������ (�� 1 �� 5, ������ "������ - ����������");
    Integer currentScore;  //������� ������ ������ ��������
}
