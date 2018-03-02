package com.cryptoconverter.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan({"com.cryptoconverter.api","com.cryptoconverter.uniquesecurity"}) // for activate only 1 user access (bonus 1, francesco : exmachina)
@EnableScheduling
public class CryptoConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoConverterApplication.class, args);
	}
}


