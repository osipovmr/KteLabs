package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.StatisticPerson;

public interface StatisticPersonRepository extends JpaRepository<StatisticPerson, Integer> {
}
