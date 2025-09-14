package com.infnet.spring_security;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// Interface para incritar a senha de forma segura
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	// Encripted password for user using BCrypt encoder
    // Roda automaticamente quando a aplicação inicia, recebendo o PasswordEncoder configurado no SecurityConfig (no caso, BCrypt).
    // Faz a encritaçã oda senha e com isso nunca guardamos a senha em texto puro, só a versão criptografada.
	@Bean
	ApplicationRunner runner(PasswordEncoder passwordEncoder) {
		return args -> System.out.println(passwordEncoder.encode("password"));
	}
}
