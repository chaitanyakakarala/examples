package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	JavaMailSender mailSender;
	
	@PostConstruct
	public void sendMail() {
		
		GreenMail greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.start();
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("test subject");
        message.setText("sample");
        message.setTo("sample");
        message.setFrom("from");

        mailSender.send(message);
        System.out.println("-------->Mail has been sent<--------");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
