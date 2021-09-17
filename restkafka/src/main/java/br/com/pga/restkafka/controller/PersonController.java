package br.com.pga.restkafka.controller;


import br.com.pga.restkafka.dto.PersonResponseDTO;
import br.com.pga.restkafka.model.Person;
import br.com.pga.restkafka.services.PersonServices;
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


    PersonServices personService;


    @GetMapping

    public List<Person> list() {

        return personService.listPersons();

    }

    @GetMapping("/{id}")

    public Person returnOne(@PathVariable Long id) {

        return personService.returnOne(id);

    }

    @PostMapping


    public Person register(@RequestBody Person person) {

        return personService.addOne(person);

    }

    @PutMapping("/{id}")

    public Person update(@PathVariable Long id, @RequestBody PersonResponseDTO personResponseDTO) {

        return personService.update(id, personResponseDTO);

    }


    @DeleteMapping("/{id}")

    public void remove(@PathVariable Long id) {

        personService.remove(id);

    }


}
