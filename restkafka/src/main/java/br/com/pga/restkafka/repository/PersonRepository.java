package br.com.pga.restkafka.repository;

import br.com.pga.restkafka.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findByFirstName(String name, Pageable pageable);

}
