package com.example.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/api/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

}
