package com.mailflow1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class MailflowProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(MailflowProject1Application.class, args);
    }

}
