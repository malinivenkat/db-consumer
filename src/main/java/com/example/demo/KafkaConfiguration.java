package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConfiguration {
	
	@Bean
    public Map<String, Object> consumerConfigs() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDeSerializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-grp");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return props;
    }

	@Bean
	public ConsumerFactory<Integer, Order> consumerFactory() {
		return new DefaultKafkaConsumerFactory<Integer, Order>(consumerConfigs(), new IntegerDeserializer(),
				new OrderDeSerializer());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, Order> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, Order> factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}


	@Bean
	public DBConsumerService dbConsumerService() {
		return new DBConsumerService();
	}
}
