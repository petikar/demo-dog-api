package com.example.demo.service;

import com.example.demo.model.Person;

import java.util.List;

public interface PersonService {
    void save(Person newPerson);

    List<Person> findAll();

    Person findById(int id);
}

