package com.example.demo.loop;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
public class DemoApplication1 {

	@Autowired
	MyService myService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication1.class, args);
	}

	@Autowired
	public void run() {
		myService.runService();
	}

}
