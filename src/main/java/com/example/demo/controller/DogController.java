package com.example.demo.controller;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.dto.DogInfoDto;
import com.example.demo.dto.transfer.DogInfo;
import com.example.demo.dto.transfer.Exist;
import com.example.demo.dto.transfer.FindByName;
import com.example.demo.dto.transfer.New;
import com.example.demo.service.DogService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogService service;

    //+TODO Эта аннотация на конструкторе не нужна
    public DogController(DogService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DogDto>> getAll() {
        List<DogDto> dogs = service.findAll();
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DogDtoWithComment> getDog(@PathVariable("id") int id) {
        DogDtoWithComment dog = service.findById(id);
        // + TODO Это не ответственность контроллера делать эту конвертацию
        return new ResponseEntity<>(dog, HttpStatus.OK); //если собака не найдена, то ошибку возвращает сервис. Так можно?
        //TODO Это вопрос того используется ли сервис еще где-то, может ли вернуть null. Если в других частях программы Exception обрабатывается, то можно
    }

    //    если передаю id или чего-то не хватает:  MethodArgumentNotValidException
    @PostMapping
    @JsonView({Exist.class})
    public ResponseEntity<DogDto> createDog(@Validated(New.class) @RequestBody DogDto newDog) {
        //+ TODO В Dog id - GeneratedValue. А что будет, если он будет передан в DogDto?
        return new ResponseEntity<>(service.save(newDog), HttpStatus.CREATED);
    }

    //+ TODO findByName, тип POST.
    // + На входе body: {"name": "some name"}
    // + На выходе: DogInfoDto

    @PostMapping("/name") //BeanCreationException: cлучилось 2 пост запроса на один адрес
    @JsonView({DogInfo.class})
    public ResponseEntity<List<DogInfoDto>> findByName(@Validated(FindByName.class) @RequestBody DogDto nameForSearch) {
        String name = nameForSearch.getName();
        List<DogInfoDto> dogs = service.findByName(name);
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }
}
