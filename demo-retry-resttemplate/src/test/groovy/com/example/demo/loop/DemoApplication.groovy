package com.example.demo.loop

import org.hamcrest.Matchers
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class DemoApplication extends Specification{

    public void "test to retry rest template" () {
        given:""
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("Test response",HttpStatus.ACCEPTED);

        and: ""
        RestTemplate restTemplate = Stub(RestTemplate.class)
        restTemplate.getForEntity(Mockito.anyString(), Matchers.any()) >> responseEntity

        and : ""
        DemoApplication1 application1 = new DemoApplication1();
        application1.resturl = "http://localhost:8080/parties"
        application1.breakcnt=10
        application1.iteratecnt=3

        when: ""
        application1.run();

        then: ""

    }

    public void "test to retry rest template positive" () {

        given:" creating return variable for mocking"
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("Test response",HttpStatus.ACCEPTED);

        and: " mockking the rest template call"
        RestTemplate restTemplate = Mock {
            getForEntity("http://localhost:8080/parties", String.class) >> responseEntity
        }

        and : "create object for class to be tested"
        DemoApplication1 application1 = new DemoApplication1();
        application1.restTemplate = restTemplate;
        application1.resturl = "http://localhost:8080/parties"
        application1.breakcnt=10
        application1.iteratecnt=3

        when: "call method to be tested from actual object"
        application1.run();

        then: "write some asserts"

    }

}
