package com.webflux.example.webfluxdemo;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EmployeeWebClient {

    WebClient client = WebClient.create("http://localhost:8090");
    
    public void consume() {

//        Mono<Employee> employeeMono = client.get()
//            .uri("/employees/{id}", "1")
//            .retrieve()
//            .bodyToMono(Employee.class);
//
//        employeeMono.subscribe(System.out::println);
        
        Flux<Employee> employeeFlux = client.get()
            .uri("/employees")
            .retrieve()
            .bodyToFlux(Employee.class);
        
        employeeFlux.subscribe(System.out::println);
        
     // Creates a flux that generates a new value every 100 ms. 
     // The value is incremental, starting at 1.

        //        Flux<Long> intervalFlux = Flux.interval(Duration.ofMillis(1000));
//        intervalFlux.subscribe(System.out::println);
    }
}