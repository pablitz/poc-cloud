package br.com.pga.restkafka;

import br.com.pga.restkafka.model.Person;
import br.com.pga.restkafka.repository.PersonRepository;
import br.com.pga.restkafka.services.PersonService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceTest {

    Faker faker = new Faker();

    String firstName=faker.name().firstName();

    String lastName=faker.name().lastName();

    String email =faker.internet().safeEmailAddress();

    Integer age =faker.random().nextInt(1,99);

    Person person = new Person(3L,firstName,lastName,age,email,LocalDateTime.now(),LocalDateTime.now());

    List<Person> persons = Arrays.asList(person,person);



    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;


    @Test
    public void testFindAllPersons(){


        when(personRepository.findAll()).thenReturn(persons);

        List<Person> result = personService.findAll();

        Assertions.assertEquals(result,persons);

    }


    @Test
    public void testFindOne(){

        when(personRepository.getById(3L)).thenReturn(person);

        Person result = personService.findById(3L);

        Assertions.assertEquals(result,person);

    }

}
