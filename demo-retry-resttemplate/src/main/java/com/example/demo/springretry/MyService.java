package com.example.demo.springretry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

    RestTemplate template = new RestTemplate();

    int counter=0;

    @Retryable(value = {ResourceAccessException.class},
    maxAttempts = 3,backoff = @Backoff(2000))
   public void retryWhenException(int order) {

        try {
            System.out.println("retry");
            template.getForObject("http://localhost:8080/parties/"+order,String.class);
        }catch (ResourceAccessException excep) {
            throw excep;
        }

   }

   @Recover
   public void recover(ResourceAccessException excep) {
        counter = counter+3;
       System.out.println(excep.getMessage());
       if (counter>=10)
           throw new RuntimeException(excep);
   }

}
