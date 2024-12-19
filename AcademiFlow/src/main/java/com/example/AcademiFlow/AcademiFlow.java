package com.example.AcademiFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.AcademiFlow.repository")
@EntityScan(basePackages = "com.example.AcademiFlow.entity")
public class AcademiFlow {


    public static void main(String[] args) {
        SpringApplication.run(AcademiFlow.class, args);
    }


}
