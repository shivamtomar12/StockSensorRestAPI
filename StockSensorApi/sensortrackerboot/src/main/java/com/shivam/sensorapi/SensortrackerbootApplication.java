package com.shivam.sensorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SensortrackerbootApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SensortrackerbootApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SensortrackerbootApplication.class);
	}
	
	
}
