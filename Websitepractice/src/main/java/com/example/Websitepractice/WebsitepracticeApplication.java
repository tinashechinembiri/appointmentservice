package com.example.Websitepractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// appointment microservice, login  and order microservice
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.example.Websitepractice.appointment"})
//@EnableJpaRepositories(basePackages = {"com.example.Websitepractice.appointment.service"})
public class WebsitepracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsitepracticeApplication.class, args);
	}


}
