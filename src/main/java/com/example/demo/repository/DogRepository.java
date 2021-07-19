package com.example.demo.repository;

import com.example.demo.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {

    //TODO пробросить его в сервис и контроллер
    // А что будет если будет несколько Dog с одинаковым именем?
    Dog findByName (String Name);

}
