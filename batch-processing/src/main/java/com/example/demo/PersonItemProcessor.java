package com.example.demo;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person>{

	@Override
	public Person process(Person person) throws Exception {

		person.setFirstName(person.getFirstName().toUpperCase());
		person.setLastName(person.getLastName().toUpperCase());

		return person;
	}

}
