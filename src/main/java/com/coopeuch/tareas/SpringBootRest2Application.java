package com.coopeuch.tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication

public class SpringBootRest2Application   implements WebMvcConfigurer {

	public static void main(String[] args) {
		   SpringApplication.run(SpringBootRest2Application.class, args);
	}

}
