package osipovmr.KteLabs.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Товар (Идентификатор, наименование, Цена, описание, оценки покупателей)
 */
@Entity
@Table(name = "product_table")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer product_id;
    @NotBlank
    String productName;
    @NonNull
    Long price;
    String productDiscription;
    String personScore;
}
