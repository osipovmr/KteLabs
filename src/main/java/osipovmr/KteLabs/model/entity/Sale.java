package osipovmr.KteLabs.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ‘акт продажи (идентификатор клиента, дата продажи, номер чека, список позиций).
 * ѕозици€ - идентификатор товара, кол-во, исходна€ цена (дл€ заданного кол-ва товаров),
 * конечна€ цена, конечна€ скидка (%).
 */
@Entity
@Table(name = "sale_table")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer sale_id;
    @ManyToOne
    Person person;
    LocalDateTime saleDate;
    String receiptNumber;
    @OneToMany
    List<Position> positions;
}
