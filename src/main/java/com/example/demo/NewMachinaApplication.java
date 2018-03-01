package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan({"com.example.demo","com.example.security2"}) // IS FOR ACTIVATE TASK 1 EXTRA
@EnableScheduling
public class NewMachinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewMachinaApplication.class, args);
	}
}


//--EXTRA NOTE--//
//@ComponentScan({"com.example.demo","com.example.quartz"}) //in case of use different package quartz
//@EnableJpaRepositories(basePackages={"com.example.demo","com.example.jpa"}) //in case of use different package JPA
