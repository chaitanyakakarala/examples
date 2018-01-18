package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class LoginRestController {

	@RequestMapping(method=RequestMethod.POST, path="/login/{username}")
	public ResponseEntity<String> login(@PathVariable(name="username" ,required = true) String username,
			@RequestBody User jsonInput) {
		
		System.out.println("This is username "+ username);
		System.out.println(jsonInput.getUsername());
		System.out.println(jsonInput.getPassword());
		
		return ResponseEntity.ok(new Gson().toJson(jsonInput));
	}
	
	
	
	
}
