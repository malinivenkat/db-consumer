package com.example.demo;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class DBConsumerController {
	
	private OrderRepository orderRepository;
	
	public DBConsumerController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@PostMapping(path = "/add")
	public @ResponseBody
	Mono<Order> addProduct(@RequestBody Order order) {
		return orderRepository.save(order);
	}
	
	@GetMapping(path = "/get/{orderId}")
	public Mono<Order> getOrder(@PathVariable int orderId) {
		return orderRepository.findById(orderId);
	}
	
	@GetMapping(path = "/getall")
	public Flux<Order> getAllOrders() {
		return orderRepository.findAll();
	}	
	
}
