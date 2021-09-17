package br.com.pga.restkafka.services;

import br.com.pga.restkafka.dto.PersonResponseDTO;
import br.com.pga.restkafka.model.Person;
import br.com.pga.restkafka.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServices {

    PersonRepository personRepository;

    public List<Person> listPersons(){

       return personRepository.findAll();

    }


    public Person returnOne(long id){

        return  personRepository.getById(id);

    }

    public Person addOne(Person person){

        return personRepository.save(person);

    }

    public Person update(Long id,PersonResponseDTO personResponseDTO){

        Person person = personRepository.getById(id);

        person.setFirstName(personResponseDTO.getFirstName());
        person.setSurName(personResponseDTO.getSurName());
        person.setAge(personResponseDTO.getAge());
        person.setUpdated_at(LocalDateTime.now());
        person.setEmail(personResponseDTO.getEmail());

        personRepository.save(person);

        return person;

    }

    public void remove(Long id){

        Optional<Person> optional = personRepository.findById(id);

        if (optional.isPresent()) {

            personRepository.deleteById(id);

        }

    }

}
