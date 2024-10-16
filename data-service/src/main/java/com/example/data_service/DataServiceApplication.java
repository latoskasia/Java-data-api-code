package com.example.data_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "PUT", "POST", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

}
