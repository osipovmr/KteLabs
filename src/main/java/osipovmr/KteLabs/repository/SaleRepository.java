package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.model.entity.Sale;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findAllBySaleDate(String date);
    List<Sale> findAllByPerson(Person person);
}
