package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    public Optional<Person> findByUserName(String username) {
        return personRepository.findPersonByUsername(username);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public void update(Person person) {
        personRepository.update(person.getId(), person.getUsername());
    }

    public void delete(Integer id) {
        personRepository.delete(personRepository.findById(id).get());
    }

}
