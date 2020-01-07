package com.webflux.example.webfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.FluxSink;

@SpringBootApplication
public class WebfluxdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxdemoApplication.class, args);
		
//		EmployeeWebClient client = new EmployeeWebClient();
//		client.consume();
	}
	
	@Bean
	public FluxSink<String> getSink(EmitterProcessor<String> emitterProcessor) {
		return emitterProcessor.sink();
	}
	
	@Bean
	public EmitterProcessor<String> streamStatusEmitter() {
		return EmitterProcessor.create();
	}

}
