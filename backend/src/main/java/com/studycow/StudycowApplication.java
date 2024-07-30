package com.studycow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class StudycowApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudycowApplication.class, args);
	}

}
