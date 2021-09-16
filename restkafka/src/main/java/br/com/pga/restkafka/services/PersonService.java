package br.com.pga.restkafka.services;


import br.com.pga.restkafka.dto.PagePersonDTO;
import br.com.pga.restkafka.dto.PersonDTO;
import br.com.pga.restkafka.dto.PersonResponseDTO;
import br.com.pga.restkafka.model.Person;
import br.com.pga.restkafka.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public static Page<PersonDTO> convert(Page<Person> Persons) {
        return Persons.map(PersonDTO::new);
    }

    public Person convertResponse(PersonResponseDTO personResponseDTO) {

        return Person.builder()
                .firstName(personResponseDTO.getFirstName())
                .surName(personResponseDTO.getSurName())
                .age(personResponseDTO.getAge())
                .email(personResponseDTO.getEmail())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

    }


    public Person convertResponseUpdate(long id, PersonResponseDTO personResponseDTO) {

        Person person = personRepository.getOne(id);

        person.setAge(personResponseDTO.getAge());

        person.setEmail(personResponseDTO.getEmail());

        person.setSurName(personResponseDTO.getSurName());

        person.setFirstName(personResponseDTO.getFirstName());

        person.setUpdated_at(LocalDateTime.now());

        return person;
    }

    public Page<PersonDTO> listPersons(String namePerson, Pageable paginacao) {

        if (namePerson == null) {

            PagePersonDTO pagePersonDTO = new PagePersonDTO();

            Page<Person> Persons = personRepository.findAll(paginacao);

            pagePersonDTO.setPersons(Persons);

            return convert(Persons);
        } else {

            PagePersonDTO pagePersonDTO = new PagePersonDTO();

            Page<Person> Persons = personRepository.findByFirstName(namePerson, paginacao);

            pagePersonDTO.setPersons(Persons);

            return convert(Persons);
        }

    }

    public ResponseEntity<PersonDTO> response(PersonResponseDTO personResponseDTO, UriComponentsBuilder uriBuilder) {

        Person person = convertResponse(personResponseDTO);

        personRepository.save(person);

        URI uri = uriBuilder.path("/Persons/{id}").buildAndExpand(person.getId()).toUri();

        return ResponseEntity.created(uri).body(new PersonDTO(person));

    }


    public ResponseEntity<PersonDTO> update(Long id, PersonResponseDTO personResponseDTO) {

        Optional<Person> optional = personRepository.findById(id);

        if (optional.isPresent()) {

            Person person = convertResponseUpdate(id, personResponseDTO);

            return ResponseEntity.ok(new PersonDTO(person));
        }

        return ResponseEntity.notFound().build();

    }


    public ResponseEntity<?> remove(Long id) {
        Optional<Person> optional = personRepository.findById(id);

        if (optional.isPresent()) {

            personRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}
