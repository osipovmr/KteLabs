package osipovmr.KteLabs.service;

import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.repository.PersonRepository;

import java.util.List;

@Service
public interface PersonService {
    List<Person> findAllPerson();
}
