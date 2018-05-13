package com.example.demo.springretry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
public class DemoApplication {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retry = new RetryTemplate();

        Map<Class<? extends Throwable>, Boolean> stopExceptions =
                Collections.singletonMap(InterruptedException.class, Boolean.FALSE);
        SimpleRetryPolicy retryPolicy =
                new SimpleRetryPolicy(10, stopExceptions, true, true);


        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();

        backOffPolicy.setInitialInterval(10000);
        //backOffPolicy.setMultiplier(2.0);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);

        return template;
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    RestTemplate template = new RestTemplate();

    @PostConstruct
    public void run(){
        retryTemplate().execute(context -> {
            System.out.println("we are in retry");
            System.out.println(">>>>---->"+template.getForEntity("http://localhost:8080/parties/1",String.class));
            return null;
        });
    }
}
