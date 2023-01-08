package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.model.entity.Product;
import osipovmr.KteLabs.model.entity.StatisticPerson;
import osipovmr.KteLabs.model.entity.StatisticProduct;

public interface StatisticPersonRepository extends JpaRepository<StatisticPerson, Integer> {
    StatisticPerson findStatisticPersonByPerson(Person person);
}
