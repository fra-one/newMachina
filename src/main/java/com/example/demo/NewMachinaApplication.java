package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
//@ComponentScan({"com.example.demo","com.example.quartz"}) //in case of use different package quartz
//@EnableJpaRepositories(basePackages={"com.example.demo","com.example.jpa"}) //in case of use different package JPA
@EnableScheduling
public class NewMachinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewMachinaApplication.class, args);
	}
}
