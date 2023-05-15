package com.gofinancas;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class StartaApis {
    public static void main(String[] args) {

        SpringApplication.run(StartaApis.class, args);
    }
}
