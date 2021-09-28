package br.com.pga.restkafka;

import br.com.pga.restkafka.faker.PersonFactory;
import br.com.pga.restkafka.model.Person;
import br.com.pga.restkafka.repository.PersonRepository;
import br.com.pga.restkafka.services.PersonService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PersonServiceIntegrationTest {

    Faker faker = new Faker();

    PersonFactory personFactory = new PersonFactory(faker);

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @BeforeEach
    public void clean(){
        personRepository.deleteAll();
        personRepository.flush();
    }



    @Test
    public void getAllTest(){


        List<Person> people = Arrays.asList(personFactory.build(), personFactory.build(), personFactory.build());

        personRepository.saveAll(people);

        assertEquals(people.get(0).getFirstName(),personService.findAll().get(0).getFirstName()) ;

        assertEquals(people.get(1).getFirstName(),personService.findAll().get(1).getFirstName()) ;

        assertEquals(people.get(2).getFirstName(),personService.findAll().get(2).getFirstName()) ;

    }


    @Test
    public void getByIDTest(){

        Person person = personFactory.build();

        personRepository.save(person);

        assertEquals(person.getFirstName(),personService.findById(3L).getFirstName()) ;

    }


    @Test
    public void putTest(){

        Person person = personFactory.build();

        assertEquals(person.getFirstName(),personService.create(person).getFirstName()) ;

        assertEquals(person.getFirstName(),personRepository.findAll().get(0).getFirstName());

    }


    @Test
    public void deleteTest(){

        Person person = personFactory.build();

        personRepository.save(person);

        assertEquals(person.getFirstName(),personRepository.findAll().get(0).getFirstName());

        Long id = personRepository.findAll().get(0).getId();

        personService.remove(personRepository.findAll().get(0).getId());

        assertThrows(JpaObjectRetrievalFailureException.class,()->personService.findById(id));


    }



    @Test
    public  void update(){

        Person person = personFactory.build();

        personRepository.save(person);

        Person person1 = personFactory.build();

        Long id = personRepository.findAll().get(0).getId();

        person1.setId(id);

        Person person2 = personService.update(person1);

        assertEquals(person2.getFirstName(),person1.getFirstName());

        assertEquals(person2.getFirstName(),personRepository.findById(id).get().getFirstName());

    }

}
