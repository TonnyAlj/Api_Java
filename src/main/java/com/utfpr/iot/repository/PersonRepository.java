package com.utfpr.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.iot.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
