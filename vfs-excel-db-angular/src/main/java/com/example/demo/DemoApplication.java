package com.example.demo;

import com.example.demo.service.ReadExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ReadExcelService readExcelService;

	@PostConstruct
	public void run() {
		readExcelService.readXls("sample.xls");
	}
}
