package com.example.demo.repository;

import com.example.demo.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// + TODO Добавить метод поиска по имени
@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {

    Dog findByName (String Name);

}
