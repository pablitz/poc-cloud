package br.com.pga.restkafka.dto;


import br.com.pga.restkafka.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;
    private String firstName;
    private String surName;
    private int age;
    private String email;


    public PersonDTO(Person person){
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.surName = person.getSurName();
        this.age = person.getAge();
        this.email= person.getEmail();
    }

}
