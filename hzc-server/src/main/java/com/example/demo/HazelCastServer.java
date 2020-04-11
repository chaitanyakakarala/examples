package com.example.demo;

import com.example.demo.pojo.Customer;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;

@SpringBootApplication
public class HazelCastServer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HazelCastServer.class, args);
	}

	@Autowired
	HazelcastInstance hazelcastInstance;


	@Override
	public void run(String... args) {
		IMap<Integer, Customer> map = hazelcastInstance.getMap("customer");
		map.put(1,makeCustomer());
		System.out.println("<-------------------------------->"+map);
		System.out.println("<-------------------------------->"+map.get(1));
	}

	public Customer makeCustomer() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Krishna");
		customer.setType("Premium");

		return customer;
	}
}
