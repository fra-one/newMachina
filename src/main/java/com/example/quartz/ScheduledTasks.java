package com.example.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.Controller;
/**
 * main class where perform scheduling 
 * @author User
 *
 */
@Component
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");

	@Scheduled(fixedRate = 10000)
	public void performTask() {
		System.out.println("Regular task OVER JPA performed at "+ dateFormat.format(new Date()));
		Controller ctr = new Controller();
		ctr.getAvailableOperations();
	}

}
