package com.rabbitmq.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.documents.Subscriber;
import com.rabbitmq.resources.RabbitMqPublisher;
import com.rabbitmq.services.SubsRepoServices;

@RestController
@RequestMapping("/producer")
public class DataProducerController {
	private static final Logger LOG = Logger.getLogger(DataProducerController.class.getName());

	@Autowired
	RabbitMqPublisher producer;

	@Autowired
	SubsRepoServices subsRepoServices;

	@PostMapping(value = "/send/{msg}")
	public String sendStringMsgRabbitMq(@PathVariable(name = "msg") String msg) {
		producer.publishMsgInRabbitMq(msg);
		LOG.log(Level.INFO, msg);
		return "Plain message publish in RabbitMq successfully";
	}

	@PostMapping(value = "/publish")
	public String sendJsonMsgRabbitMq(@RequestBody Subscriber subscriber) {
		producer.publishJsonMsgInRabbitMq(subscriber);
		System.out.println("Msg publish in RabbitMq: " + subscriber);
		LOG.log(Level.INFO, "PublishUnique: "+subscriber);
		subsRepoServices.save(subscriber);
		System.out.println("Msg stored in database: " + subscriber);
		return "JSON message publish in RabbitMq and Strored in MongoDB successfully";

	}

	@GetMapping(value = "/get")
	public List<Subscriber> dummydata() {
		LOG.log(Level.INFO, subsRepoServices.find());
		return subsRepoServices.find();
	}

}
