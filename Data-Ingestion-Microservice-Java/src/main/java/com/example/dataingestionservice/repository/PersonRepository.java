package com.example.dataingestionservice.repository;


import com.example.dataingestionservice.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByUsername(String username);
    Optional<Person> findByPasswordCode(String code);

}
