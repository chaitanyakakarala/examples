package com.example.demo.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class RestServices {

    RestTemplate restTemplate;

    @Autowired
    public RestServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String callRestService(int order) {
        System.out.println(order+"<<<----");
        System.out.println(new Date());
        String body = restTemplate.getForEntity("http://localhost:8080/parties/"+order,String.class)
                .getBody();
        return body;
    }

}
