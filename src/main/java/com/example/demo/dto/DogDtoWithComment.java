package com.example.demo.dto;

import com.example.demo.model.Dog;

// + TODO Отнаследовать этот класс от Dog
public class DogDtoWithComment extends DogDto {

    private String comment;

    public DogDtoWithComment(Dog dog, String comment) {
        super(dog);
        setId(dog.getId());
        this.comment = comment;
    }

    public DogDtoWithComment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
