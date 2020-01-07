package com.webflux.example.webfluxdemo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable{

	public Employee() {
	}

	public Employee(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	private String id;
	private String name;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// standard getters and setters

}
