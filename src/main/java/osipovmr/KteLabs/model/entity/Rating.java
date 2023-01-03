package osipovmr.KteLabs.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "rating_table")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer rating_id;
    @ManyToMany
    Person person;
    @ManyToMany
    Product product;
    @Max(value = 5)
    @Min(value = 0)
    Integer score;
}
