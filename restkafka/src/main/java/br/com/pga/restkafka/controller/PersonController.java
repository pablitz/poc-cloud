package br.com.pga.restkafka.controller;

import br.com.pga.restkafka.model.Person;
import br.com.pga.restkafka.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {


    private final PersonService personService;


    @GetMapping
    public List<Person> list() {

        return personService.findAll();

    }

    @GetMapping("/{id}")
    public Person returnOne(@PathVariable Long id) {

        return personService.findById(id);

    }

    @PostMapping
    public Person register(@RequestBody Person person) {

        return personService.create(person);

    }

    @PutMapping("/{id}")
    public Person update(@RequestBody Person person) {

        return personService.update(person);

    }


    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {

        personService.remove(id);

    }


}
