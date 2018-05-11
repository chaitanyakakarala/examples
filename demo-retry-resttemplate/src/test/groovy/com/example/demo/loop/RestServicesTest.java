package com.example.demo.loop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestServicesTest {

    @Mock
    RestTemplate restTemplate;

    RestServices restServices = new RestServices(restTemplate);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        restServices.restTemplate = restTemplate;
    }

    @Test
    public void callRestServiceTest(){
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
                .thenReturn(new ResponseEntity<>("test", HttpStatus.ACCEPTED));
        String body = restServices.callRestService(1);
        Assert.assertEquals(body, "test");

    }


}
