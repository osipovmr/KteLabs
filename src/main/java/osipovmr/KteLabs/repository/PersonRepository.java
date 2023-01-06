package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findPersonById(Integer personId);
}
