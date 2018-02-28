package com.example.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

//@EnableJpaRepositories
@SpringBootApplication
public class SpringBootJpaAloneSimple {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaAloneSimple.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CurrencyRepository repository) { // CREATE 
		return (args) -> {
			repository.save(new Currency("BTC", 12.1 ,10));
		};
	}
}
