package com.example.ms_episodios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsEpisodiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEpisodiosApplication.class, args);
	}

}
