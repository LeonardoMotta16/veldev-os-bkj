package com.veldev.veldevosbkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VeldevOsBkjApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeldevOsBkjApplication.class, args);
    }

}
