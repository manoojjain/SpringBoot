package com.rabbitmq.resources;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.documents.Subscriber;

@Component
public class RabbitMqPublisher {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;

	@Value("${jsa.rabbitmq.routingkey}")
	private String routingKey;

	public void publishMsgInRabbitMq(String Get_order) {
		amqpTemplate.convertAndSend(exchange, routingKey, Get_order);
		System.out.println("Producer.publishMsgInRabbitMq(): " + Get_order.toString());
	}

	public void publishJsonMsgInRabbitMq(Subscriber nokiaSubs) {
		amqpTemplate.convertAndSend(exchange, routingKey, nokiaSubs.toString());
		System.out.println("Producer.publishJsonMsgInRabbitMq(): " + nokiaSubs.toString());
	}
}
