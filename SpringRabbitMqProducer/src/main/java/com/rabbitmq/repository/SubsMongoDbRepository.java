package com.rabbitmq.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rabbitmq.documents.Subscriber;

@Repository
public interface SubsMongoDbRepository extends MongoRepository<Subscriber, String> {
	
}