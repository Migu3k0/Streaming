package com.example.ms_series;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsSeriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSeriesApplication.class, args);
	}

}
