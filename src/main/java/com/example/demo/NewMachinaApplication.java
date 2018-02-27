package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.example.quartz.ScheduledTasks;

@SpringBootApplication
@ComponentScan({"com.example.demo","com.example.quartz"})
@EnableScheduling
public class NewMachinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewMachinaApplication.class, args);
	}
}
