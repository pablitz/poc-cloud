package br.com.pga.restkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {

    private String firstName;

    private String surName;

    private String email;

    private Integer age;


}
