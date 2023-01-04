package osipovmr.KteLabs.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * ѕозици€ - идентификатор товара, кол-во, исходна€ стоимость, конечна€ стоимость, конечна€ скидка (%).
 */
@Entity
@Table(name = "position_table")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @NotNull
    Long value;
    Long startCost;
    Long finishCost;
    Integer finishDiscount;
}
