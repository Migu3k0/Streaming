package com.example.ms_favoritos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsFavoritosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFavoritosApplication.class, args);
	}

}
