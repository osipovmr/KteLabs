package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
