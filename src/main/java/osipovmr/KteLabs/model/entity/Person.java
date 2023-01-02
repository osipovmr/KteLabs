package osipovmr.KteLabs.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
/**
 * Клиент (идентификатор, имя, индивидуальная скидка 1, индивидуальная скидка 2).
 */
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "person_table")
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer person_id;
    @NotBlank
    String personName;
    Integer firstDiscount;
    Integer secondDiscount;
}

