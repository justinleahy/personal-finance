package com.justinleahy.personalfinance;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;

@SpringBootApplication
public class PersonalFinanceApplication {

    private static Logger log = LoggerFactory.getLogger(PersonalFinanceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PersonalFinanceApplication.class, args);
    }
}
