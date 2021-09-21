package br.com.pga.restkafka;

import br.com.pga.restkafka.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

   @Autowired
   private TestRestTemplate testRestTemplate;

   @LocalServerPort
   int randomServerPort;

   @Test
   @Sql("/data.sql")
   public void getPersons(){

       ResponseEntity<Person> person = testRestTemplate.getForEntity("/persons/2",Person.class);

       assertEquals("joao",person.getBody().getFirstName());

       assertEquals("da silva",person.getBody().getSurName());

       assertEquals(27,person.getBody().getAge());

       assertEquals("joaodasilva@gmail.com",person.getBody().getEmail());

   }



    @Test
    @Sql("/data.sql")
    public void postPersons() throws URISyntaxException {

        final String baseUrl = "http://localhost:"+ randomServerPort +"/persons";
        URI uri = new URI(baseUrl);

        Person person = new Person(3L,"jose","da silva",27 ,"joaodasilva@gmail.com",LocalDateTime.now(), LocalDateTime.now());

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Person> request = new HttpEntity<>(person, headers);

        ResponseEntity<String> result = this.testRestTemplate.postForEntity(uri, request, String.class);

        assertEquals(200, result.getStatusCodeValue());

    }


    @Test
    @Sql("/data.sql")
    public void updatePersons() throws URISyntaxException {

        final String baseUrl = "http://localhost:"+ randomServerPort +"/persons";
        URI uri = new URI(baseUrl);

        Person person = new Person(3L,"jose","da silva",27 ,"joaodasilva@gmail.com",LocalDateTime.now(), LocalDateTime.now());

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Person> request = new HttpEntity<>(person, headers);

        this.testRestTemplate.put(uri, request);

        //assertEquals(200, result.getStatusCodeValue());


    }


}
