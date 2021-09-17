package br.com.pga.restkafka.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="FIRSTNAME")
    private String firstName;
    @Column(name="SURNAME")
    private String surName;
    @Column(name="AGE")
    private int age;
    @Column(name="EMAIL")
    private String email;
    @Column(name="CREATED_AT")
    private LocalDateTime created_at;
    @Column(name="UPDATED_AT")
    private LocalDateTime updated_at;

}



