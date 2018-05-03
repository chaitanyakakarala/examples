package com.example.demo.loop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
public class DemoApplication1 {



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication1.class, args);
	}

	RestTemplate template = new RestTemplate();

	@PostConstruct
	public void run(){

		int trailno=0; // keep global variable to check trail number for retrail

		for (int order=1;order<=10;order++) { ///loop through 10 orders
			System.out.println("Current order "+order); //print current order


			for (int retrail = 1;retrail<=3;retrail ++) { // loop through 3 trails

				try {
					System.out.println(retrail);
					String resp = template.getForEntity("http://localhost:8080/parties/"+order,
							String.class).getBody(); // hit the end point
					System.out.println(resp);
					trailno =0; // if success then it will not go to catch and breaks current order retry and
					//goes to next
					break;
				}catch (Exception exp) {
					trailno ++; // if the attemp fails ... increase trailno and loop for retry again..
					//if retry > 3 ... goes to next order

				}
			}
			System.out.println(trailno);
			if (trailno >=10) {
				break;
			}
		}


	}

}
