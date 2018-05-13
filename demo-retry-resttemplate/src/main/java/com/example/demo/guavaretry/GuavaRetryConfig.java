package com.example.demo.guavaretry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class GuavaRetryConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Retryer<Boolean> retryer(){

        Predicate<Boolean> predicate = new Predicate<Boolean>() {
            @Override
            public boolean apply(Boolean input) {
                return !input;
            }
        };

       return RetryerBuilder.<Boolean>newBuilder().retryIfResult(predicate)
                        .withWaitStrategy(WaitStrategies.exponentialWait(60000,5,TimeUnit.MINUTES))
                        .withStopStrategy(StopStrategies.stopAfterAttempt(5)).build();

    }

}
