package com.example.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootQuartzAloneSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(
				new Object[] { SpringBootQuartzAloneSimple.class }, args);
	}

}
