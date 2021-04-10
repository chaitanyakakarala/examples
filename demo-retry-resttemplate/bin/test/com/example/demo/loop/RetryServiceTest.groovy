package com.example.demo.loop

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import javax.xml.ws.Response

class RetryServiceTest extends Specification{



    def "positive case"() {

        given: "Declared all out variables "
        ResponseEntity<String> responseEntity = new ResponseEntity<>("test",HttpStatus.ACCEPTED);

        and : " Mocked the rest template call"
        RestTemplate restTemplate = Mock {
            getForObject(any(),String.class) >> responseEntity
        }

        and: "Create o"
        RetryService retryService = new RetryService()
        retryService.restTemplate = restTemplate
        retryService.retryString="1,2,3"
        retryService.consecutiveFailsCnt=10
        retryService.multiplier=10000
        retryService.restUrl="http://localhost:8080/parties/"

        when: ""
        retryService.doretry()

        then: ""
        //retryService.restUrl=="http://localhost:8080/parties/"


    }

}
