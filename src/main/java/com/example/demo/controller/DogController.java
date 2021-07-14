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

    //TODO Сделать DTO, и маппер/коневертер
    // Обработка ошибок через ExceptionHandler

    //TODO Через конструктор
    @Autowired
    private DogService service;

    @GetMapping
    public ResponseEntity<List<Dog>> getAll() {

        List<Dog> dogs = service.findAll();
        //TODO NOT_FOUND обычно относится к конкретной записи, для списков лучше возвращать пустой список
        if (dogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(dogs, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDog(@PathVariable int id) {

        //TODO 1. Параметры приложения перенести в yaml
        // 2. Сделать класс для хранения каких-то параметров приложения
        // 3. Один параметр comment
        // 4. Значение параметра должно браться из конфигурационного файла
        // 5. И выдаваться в ответе только этого интерфейса вместе с сущностью dog
//        {
//            "id": 0,
//            "name": "",
//            "age": 0.00,
//            "comment": "значение из конфига"
//        }

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
