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
@Table(name = "statistic_person_table")
@NoArgsConstructor
public class StatisticPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne
    @JoinColumn(name = "person_id")
    Person person;
    Integer receiptValue;   //кол-во чеков;
    Long cost;  //общая стоимость по чекам по исходной цене
    Long discountSum;   //сумма скидок по всем позициям чеков
}
