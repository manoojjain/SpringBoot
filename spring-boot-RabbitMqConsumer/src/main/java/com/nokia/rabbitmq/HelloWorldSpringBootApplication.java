package com.nokia.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nokia.rabbitmq.resources.RabbitMqConsumer;

@SpringBootApplication
public class HelloWorldSpringBootApplication {

	@Autowired 
	RabbitMqConsumer consumer;
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldSpringBootApplication.class, args);
	}
}
