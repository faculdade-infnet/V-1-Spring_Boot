package com.infnet.applicationexception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationExceptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationExceptionApplication.class, args);
        System.out.println("API Executando");
	}
}
