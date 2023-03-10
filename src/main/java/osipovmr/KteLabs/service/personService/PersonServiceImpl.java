package osipovmr.KteLabs.service.personService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.exception.BadRequestException;
import osipovmr.KteLabs.model.dto.request.ChangeDiscountRequest;
import osipovmr.KteLabs.model.dto.response.PersonDto;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    @Autowired
    private final PersonRepository personRepository;

    @Override
    public ResponseEntity<?> changeDiscount(ChangeDiscountRequest dto) {
        Person person = personRepository.findPersonById(dto.getPersonId());
        System.out.println(dto.toString());

        if (isNull(person)) {
            throw new BadRequestException("Person with id " + dto.getPersonId() + " not found");
        } else {
        person.setFirstDiscount(dto.getFirstDiscount());
        person.setSecondDiscount(dto.getSecondDiscount());
        personRepository.save(person);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @Override
    public PersonDto personDtoMapper(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setPersonId(person.getId());
        personDto.setPersonName(person.getPersonName());
        personDto.setFirstDiscount(person.getFirstDiscount());
        personDto.setSecondDiscount(person.getSecondDiscount());
        return personDto;
    }

    @Override
    public List<PersonDto> findAllPerson() {
        List<Person> allPerson = personRepository.findAll();
        List<PersonDto> allPersonDto = allPerson.stream().map(this::personDtoMapper).collect(Collectors.toList());
        return allPersonDto;
    }
}
