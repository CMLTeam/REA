package com.cmlteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.cmlteam")
@EnableScheduling
public class ReaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReaApplication.class, args);
	}
}
