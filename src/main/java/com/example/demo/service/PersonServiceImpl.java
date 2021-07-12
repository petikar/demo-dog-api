package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    private PersonRepository repository;


    @Override
    public void save(Person newPerson) {
        repository.save(newPerson);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findById(int id) {
        return repository.findById(id).get();
    }
}
