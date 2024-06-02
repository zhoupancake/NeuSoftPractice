package com.system.neusoftpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.system")
public class NeuSoftPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeuSoftPracticeApplication.class, args);
	}

}
