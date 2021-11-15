package com.example.Websitepractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// appointment microservice, login  and order microservice
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.example.Websitepractice.appointment","com.controller"})
@EnableMongoRepositories(basePackages = "com.example.domain.repositories.mongodb")
//@EnableJpaRepositories(basePackages = {"com.example.Websitepractice.appointment.service"})
public class WebsitepracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsitepracticeApplication.class, args);
	}
}
