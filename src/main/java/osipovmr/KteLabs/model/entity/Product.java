package osipovmr.KteLabs.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

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
    Integer id;
    @NotBlank
    String productName;
    @NonNull
    Long price; //в копейках
    String productDescription;
    @OneToMany(mappedBy = "product")
    List<Rating> ratingList;    //список оценок покупателей
    Integer discount;   //скидка
}
