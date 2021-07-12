package com.example.demo.controller;

import com.example.demo.model.Dog;
import com.example.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private DogService service;

    @GetMapping
    public ResponseEntity<List<Dog>> getAll() {

        List<Dog> dogs = service.findAll();
        if (dogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(dogs, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDog(@PathVariable int id) {

        Dog dog = service.findById(id);

        if (dog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(dog, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody Dog newDog) {
        service.save(newDog);
        return new ResponseEntity<>(newDog, HttpStatus.CREATED);
    }

}
