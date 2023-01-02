package osipovmr.KteLabs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.dto.PersonDto;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    @Autowired
    private final PersonRepository personRepository;

    @Override
    public PersonDto personDtoMapper(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setPersonId(person.getPerson_id());
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
