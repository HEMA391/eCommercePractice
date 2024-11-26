package com.ecommerce.model;

public class Size {

	private String name;
	private int quantity;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Size() {
		
	}
	public Size(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	
	
}
