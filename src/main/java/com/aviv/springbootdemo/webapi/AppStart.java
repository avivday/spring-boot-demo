package com.aviv.springbootdemo.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = { "com.aviv.springbootdemo" })
public class AppStart {

	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
	}

}
