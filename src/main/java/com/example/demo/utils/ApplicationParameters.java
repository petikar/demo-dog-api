package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationParameters {

    private final String comment;

    public ApplicationParameters(@Value("${parameters.dog.comment}") String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


}
