package osipovmr.KteLabs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osipovmr.KteLabs.model.entity.Person;
import osipovmr.KteLabs.repository.PersonRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    @Autowired
    private final PersonRepository personRepository;

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }
}
