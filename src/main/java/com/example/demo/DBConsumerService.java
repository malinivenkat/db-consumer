package com.example.demo;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class DBConsumerService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "order")
	public void consume(Order message) {
		latch.countDown();
//		orderRepository.save(message);
		mongoTemplate.save(message);
	}

	public Mono<Order> testSave(Order o) {
		return orderRepository.save(o);
	}

}
