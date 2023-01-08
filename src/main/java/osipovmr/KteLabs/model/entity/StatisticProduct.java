package osipovmr.KteLabs.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "statistic_product_table")
@NoArgsConstructor
public class StatisticProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;
    Integer receiptValue;   //кол-во чеков;
    Long cost;  //осоотв. позиций по исходной цене
    Long discountSum;   //сумма скидок по всем позициям чеков
}
