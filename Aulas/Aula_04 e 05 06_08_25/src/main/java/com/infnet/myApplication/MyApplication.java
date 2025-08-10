package com.infnet.myApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
        // Inicializa a aplicação SpringBoot
		SpringApplication.run(MyApplication.class, args);
        System.out.println("API executando");
	}
}
