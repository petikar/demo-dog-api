package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {

    @Value("${parameters.dog.comment}")
    private String comment;

    //+ TODO А что, если параметров будет 100?

    public String getComment() {
        return comment;
    }

}
