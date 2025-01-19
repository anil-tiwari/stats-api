package com.dunnhumby.stats_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dunnhumby"})
public class StatsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatsApiApplication.class, args);
	}

}
