package br.com.pga.restkafka;

import br.com.pga.restkafka.model.Person;
import br.com.pga.restkafka.repository.PersonRepository;
import br.com.pga.restkafka.services.PersonService;
import com.github.javafaker.Faker;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PersonServiceIntegrationTest {


    Faker faker = new Faker();

    String firstName=faker.name().firstName();

    String lastName=faker.name().lastName();

    String email =faker.internet().safeEmailAddress();

    Integer age =faker.random().nextInt(1,99);

    Person person = new Person(3L,firstName,lastName,age,email, LocalDateTime.now(),LocalDateTime.now());


    @Autowired
    PersonService personService;

    @Test
    public void getAllTest(){
        assertEquals("jose",personService.findAll().get(0).getFirstName()) ;
        assertEquals("joao",personService.findAll().get(1).getFirstName()) ;

    }


    @Test
    public void getByIDTest(){

        assertEquals("jose",personService.findById(1L).getFirstName()) ;

    }


    @Test
    public void putTest(){

        assertEquals(firstName,personService.create(person).getFirstName()) ;

    }


    @Test
    public void deleteTest(){

        personService.remove(1L);
        assertThrows(JpaObjectRetrievalFailureException.class,()->personService.findById(1L));


    }

}
