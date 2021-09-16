package br.com.pga.restkafka.model;



import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
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
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}

