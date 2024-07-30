package com.studycow;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@OpenAPIDefinition(
//		servers = {
//				@Server(url="i11c202.p.ssafy.io")
//		}
//)
@SpringBootApplication
public class StudycowApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudycowApplication.class, args);
	}

}
