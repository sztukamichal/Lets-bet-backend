package com.dtd.letsbet.repositories;

import com.dtd.letsbet.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}