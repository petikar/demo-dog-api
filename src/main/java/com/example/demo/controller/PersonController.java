package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {

        List<Person> people = service.findAll();
        if (people.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(people, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getDog(@PathVariable int id) {

        Person person = service.findById(id);

        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Person> createDog(@RequestBody Person person) {
        service.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

}
