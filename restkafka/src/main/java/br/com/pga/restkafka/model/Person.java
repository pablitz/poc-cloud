package br.com.pga.restkafka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "SURNAME")
    private String surName;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CREATED_AT")
    private LocalDateTime created_at;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updated_at;

}



