package br.com.pga.restkafka.faker;

import br.com.pga.restkafka.model.Person;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@RequiredArgsConstructor
public class PersonFactory  {

    private final Faker faker;

    public Person build() {
        return Person.builder()
                .id(faker.random().nextLong(99L))
                .firstName(faker.name().firstName())
                .surName(faker.name().lastName())
                .age(faker.random().nextInt(1,99))
                .email(faker.internet().safeEmailAddress())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
    }

}

