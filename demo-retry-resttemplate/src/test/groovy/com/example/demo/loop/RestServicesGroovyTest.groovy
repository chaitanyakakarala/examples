package com.example.demo.loop

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class RestServicesGroovyTest extends Specification{

    def "test callRestService"() {

        given: ""
        ResponseEntity<String> response = new ResponseEntity<String>("test",HttpStatus.ACCEPTED)

        and : ""
        RestTemplate restTemplate = Mock {
            getForEntity("http://localhost:8080/parties/1",String.class) >> response
        }

        and : ""
        RestServices restServices = new RestServices(restTemplate)

        when: ""
        String body = restServices.callRestService(1)

        then :""
        body.equals("test")

    }

}
