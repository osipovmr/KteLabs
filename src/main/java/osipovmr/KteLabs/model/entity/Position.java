package osipovmr.KteLabs.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * ������� - ������������� ������, ���-��, �������� ���� (��� ��������� ���-�� �������),
 * �������� ����, �������� ������ (%).
 */
@Entity
@Table(name = "position_table")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer sale_id;
    @OneToOne
    Product product;
    @NotNull
    Integer value;
    Integer startPrice;
    Integer finishPrice;
    Integer finishDiscount;
}
