package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personDAO;

    public PersonService(PersonRepository personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> findAll() {
        return (List<Person>) personDAO.findAll();
    }

    public Optional<Person> findById(int id) {
        return personDAO.findById(id);
    }

    public Person save(Person person) {
        personDAO.save(person);
        return person;
    }

    public void delete(int id) {
        Person person = new Person();
        person.setId(id);
        personDAO.delete(person);
    }
}
