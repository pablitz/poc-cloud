package br.com.pga.restkafka.controller;


import br.com.pga.restkafka.dto.PersonDTO;
import br.com.pga.restkafka.dto.PersonResponseDTO;
import br.com.pga.restkafka.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/Persons")
public class PersonController {

    @Autowired
    PersonService personService;


    @GetMapping

    public Page<PersonDTO> list(@RequestParam(required = false) String namePerson,
                                @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable) {

        return personService.listPersons(namePerson, pageable);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<PersonDTO> register(@RequestBody PersonResponseDTO form, UriComponentsBuilder uriBuilder) {

        return personService.response(form, uriBuilder);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonResponseDTO personResponseDTO) {

        return personService.update(id, personResponseDTO);

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {

        return personService.remove(id);

    }


}
