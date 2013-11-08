package com.hernsys;

public class Customer {

	private String name;
	private boolean registered;
	private float expense;
	private float discount;
	
	public Customer(){
		
	}
	
	public Customer(String name, boolean registered, float expense){
		this.name = name;
		this.registered = registered;
		this.expense = expense;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	public float getExpense() {
		return expense;
	}
	public void setExpense(float expense) {
		this.expense = expense;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	

}
