package com.example.messervice;

import com.example.messervice.entities.CD;
import com.example.messervice.entities.Catalog;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MesserviceApplication {

    @Autowired
    CamelContext camelContext;

    public static void main(String[] args) {
        SpringApplication.run(MesserviceApplication.class, args);


    }
}
