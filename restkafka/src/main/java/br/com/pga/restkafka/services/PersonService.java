package br.com.pga.restkafka.services;

import br.com.pga.restkafka.model.Person;
import br.com.pga.restkafka.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll() {

        return personRepository.findAll();

    }


    public Person findById(long id) {

        return personRepository.getById(id);

    }

    public Person create(Person person) {

        return personRepository.save(person);

    }

    public Person update(Person person) {

        Person updated_person = personRepository.getById(person.getId());

        updated_person.setFirstName(person.getFirstName());
        updated_person.setSurName(person.getSurName());
        updated_person.setAge(person.getAge());
        updated_person.setUpdated_at(LocalDateTime.now());
        updated_person.setEmail(person.getEmail());

        personRepository.save(updated_person);

        return updated_person;

    }

    public void remove(Long id) {

        Optional<Person> optional = personRepository.findById(id);

        if (optional.isPresent()) {

            personRepository.deleteById(id);

        }

    }

}
