package com.nokia.rabbitmq.resources;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMqConsumer {
	private static final Logger LOG = Logger.getLogger(RabbitMqConsumer.class.getName());

	@RabbitListener(queues = "${jsa.rabbitmq.queue}")
	public void recievedMessage(Message<?> messageEntity) {
		System.out.println("Recieved Message console: " + messageEntity.toString());
		LOG.log(Level.INFO, messageEntity.getPayload());

		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(messageEntity);
			System.out.println("ResultingJSONstring = " + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}

	}
}
