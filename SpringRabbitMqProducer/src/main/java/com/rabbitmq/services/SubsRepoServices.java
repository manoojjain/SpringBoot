package com.rabbitmq.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.documents.Subscriber;
import com.rabbitmq.repository.SubsMongoDbRepository;

@Service
public class SubsRepoServices {

	@Autowired
	SubsMongoDbRepository subsMongoDbRepostory;

	public Subscriber save(Subscriber nokiaSubs) {
		return subsMongoDbRepostory.save(nokiaSubs);
	}

	public List<Subscriber> find() {
		return subsMongoDbRepostory.findAll();
	}
}
