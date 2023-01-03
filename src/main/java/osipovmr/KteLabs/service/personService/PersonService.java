package osipovmr.KteLabs.service.personService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.request.ChangeDiscountRequest;
import osipovmr.KteLabs.model.dto.response.PersonDto;
import osipovmr.KteLabs.model.entity.Person;

import java.util.List;

@Service
public interface PersonService {
    List<PersonDto> findAllPerson();
    PersonDto personDtoMapper(Person person);
    ResponseEntity<?> changeDiscount(ChangeDiscountRequest dto);
}
