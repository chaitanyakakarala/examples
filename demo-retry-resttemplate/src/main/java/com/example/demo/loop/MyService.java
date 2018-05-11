package com.example.demo.loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

    @Autowired
    RetryTemplate retryTemplate;

    @Autowired
    RestServices restServices;

    public void withTemplate(int order) {
        retryTemplate.execute(arg0 -> {
            restServices.callRestService(order);
            return null;
        });

    }

    public void iterateOrers () {
        for (int order = 0;order <10 ; order ++) {
            withTemplate(order);
        }
    }

}
