package com.example.demo.controller;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.service.DogService;
import com.example.demo.utils.ApplicationParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogService service;
    private final ApplicationParameters applicationParameters;

    //TODO Эта аннотация на конструкторе не нужна
    @Autowired
    public DogController(DogService service, ApplicationParameters applicationParameters) {
        this.service = service;
        this.applicationParameters = applicationParameters;
    }

    @GetMapping
    public ResponseEntity<List<DogDto>> getAll() {

        List<DogDto> dogs = service.findAll();

        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DogDtoWithComment> getDog(@PathVariable("id") int id) {
        DogDto dog = service.findById(id);
        //TODO Это не ответственность контроллера делать эту конвертацию
        DogDtoWithComment dogWithComment = new DogDtoWithComment(dog, applicationParameters.getComment());


/*        if (dog == null) {
            throw new ApiRequestException("Custom ApiRequestException: Dog doesn't found by id=" + id);
        } else {
            return new ResponseEntity<>(dog, HttpStatus.OK);
        }*/
        return new ResponseEntity<>(dogWithComment, HttpStatus.OK); //если собака не найдена, то ошибку возвращает сервис. Так можно?
        //TODO Это вопрос того используется ли сервис еще где-то, может ли вернуть null. Если в других частях программы Exception обрабатывается, то можно
    }

    //проверить что передан валидный пёс
    @PostMapping
    public ResponseEntity<DogDto> createDog(@RequestBody DogDto newDog) {
        //TODO В Dog id - GeneratedValue. А что будет, если он будет передан в DogDto?
        service.save(newDog);
        return new ResponseEntity<>(newDog, HttpStatus.CREATED);
    }

    //TODO findByName, тип POST.
    // На входе body: {"name": "some name"}
    // На выходе: DogInfoDto
}
