package br.com.pga.restkafka.dto;

import br.com.pga.restkafka.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagePersonDTO {

    private Page<Person> persons;


}
