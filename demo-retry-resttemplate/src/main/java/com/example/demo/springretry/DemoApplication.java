package com.example.demo.springretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.retry.annotation.EnableRetry;

import javax.annotation.PostConstruct;

@EnableRetry
@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    MyService myService;

    @PostConstruct
    public void run() throws Exception{

        for (int i=0;i<10;i++)
            myService.retryWhenException(i);

    }
}
