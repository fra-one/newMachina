package com.cryptoconverter.uniquesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Used for test only com.cryptoconverte.uniquesecuirty components (like UniqueSecurityController) 
@SpringBootApplication
public class UniqueSecurityTestApplication {
	private static Class uniqueSecurityTestApplication = UniqueSecurityTestApplication.class;
	 
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(UniqueSecurityTestApplication.class, args);
    }

}