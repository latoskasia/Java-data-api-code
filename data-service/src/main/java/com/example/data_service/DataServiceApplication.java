package com.example.data_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(DataServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtRequestFilter> jwtFilter(){
		FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtRequestFilter());
		registrationBean.addUrlPatterns("/api/customers/*");
		return registrationBean;
	}

}
