package com.example.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

//wrapper around ReactiveCrudRepository
public interface OrderRepository extends ReactiveMongoRepository<Order, Integer>{

}
