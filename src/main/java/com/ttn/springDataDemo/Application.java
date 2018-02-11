package com.ttn.springDataDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");

			// fetch customers by first Name & last name
			log.info("Customer found with findByFirstNameAndLastName(\"Jack\", \"Bauer\"):");
			log.info("--------------------------------------------");
			for (Customer customer1 : repository.findByFirstNameAndLastName("Jack", "Bauer")) {
				log.info(customer1.toString());
			}
			log.info("");

			// fetch first customer by first Name in ascending order
			log.info("Customer found with findFirstByOrderByLastNameAsc(\"Bauer\"):");
			log.info("--------------------------------------------");
			Customer firstCustomer = repository.findFirstByOrderByLastNameAsc();
			log.info(firstCustomer.toString());
			log.info("");
		};
	}

}