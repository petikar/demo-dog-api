package com.example.demo.dto;

// + TODO Отнаследовать этот класс от Dog
public class DogDtoWithComment extends DogDto {

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
