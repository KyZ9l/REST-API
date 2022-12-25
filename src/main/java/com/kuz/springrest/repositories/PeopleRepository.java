package com.kuz.springrest.repositories;


import com.kuz.springrest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
