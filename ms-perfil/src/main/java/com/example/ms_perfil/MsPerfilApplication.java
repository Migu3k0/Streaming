package com.example.ms_perfil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPerfilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPerfilApplication.class, args);
	}

}
