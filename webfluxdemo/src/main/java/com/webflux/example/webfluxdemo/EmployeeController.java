package com.webflux.example.webfluxdemo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepositry employeeRepositry;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FluxSink<String> sink;

	@Autowired
	private EmitterProcessor<String> processor;

	@GetMapping("/{id}")
	private Mono<Employee> getEmployeeById(@PathVariable String id) {
		Employee emp = employeeRepositry.findById(id).get();
		Mono<Employee> nonEmptyMono = Mono.just(emp);
		return nonEmptyMono;
	}

	@GetMapping
	private Flux<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeRepositry.findAll();
		
		//sleep for testing if IO thread take much time in processing then it will give you response
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		System.out.println("data " + list);
		Flux<List<Employee>> nonEmptyFlux = Flux.just(list);
		return nonEmptyFlux;
	}
	
	@GetMapping(value ="/streamlive" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> getStream() {
		return processor;
	}

	@EventListener
	public void onEvent(String updatedData) {
		sink.next(updatedData);
	}

	@GetMapping("/add")
    private Employee addEmployee() {
		Employee emp = new Employee(UUID.randomUUID().toString(), "aman");
        employeeRepositry.save(emp);
        
		publisher.publishEvent(employeeRepositry.findAll().toString());
        System.out.println("Addedd new  data " + emp.toString());
        return emp;
    }

}
