package com.example.demo;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BatchProcessingApplication implements CommandLineRunner{

/*	 @Bean
	    public Step step1() {
	        return stepBuilderFactory.get("step1")
	                .<Person, Person> chunk(10)
	                .reader(reader())
	                .processor(processor())
	                .writer(writer())
	                .build();
	    }*/
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	FlatFileItemReader<Person> reader;
	
	@Autowired
	PersonItemProcessor processor;
	
	@Autowired
	JdbcBatchItemWriter<Person> writer;
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;
	
	public static void main(String[] args) {
		SpringApplication.run(BatchProcessingApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		Step step = stepBuilderFactory.get("step1")
        .<Person, Person> chunk(10)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
		
		jobBuilderFactory.get("importUserJob")
        .incrementer(new RunIdIncrementer())
       /* .listener(listener)*/
        .flow(step)
        .end()
        .build();
	}
}
