package com.hernsys;

public class Person {
	
	private String name;
	private String lastName;
	private Integer age;
	
	private Integer status;
	
	public Person(String name, String lastName, Integer age){
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

}
