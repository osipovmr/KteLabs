package osipovmr.KteLabs.service;

import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.PersonDto;
import osipovmr.KteLabs.model.entity.Person;

import java.util.List;

@Service
public interface PersonService {
    List<PersonDto> findAllPerson();
    PersonDto personDtoMapper(Person person);
}
