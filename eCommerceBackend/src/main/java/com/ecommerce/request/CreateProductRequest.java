package com.ecommerce.request;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.model.Size;

public class CreateProductRequest {
	private String title;
	private String description;
	private int price;
	private int discountedPrice;
	private int discountedPercent;
	private int quantity;
	private String brand;
	private String color;
	private Set<Size> size = new HashSet<>();
	private String imageUrl;
	private String topLevelCategory;
	private String secondLevelCategory;
	private String thirdLevelCategory;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the discountedPrice
	 */
	public int getDiscountedPrice() {
		return discountedPrice;
	}
	/**
	 * @param discountedPrice the discountedPrice to set
	 */
	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	/**
	 * @return the discountedPercent
	 */
	public int getDiscountedPercent() {
		return discountedPercent;
	}
	/**
	 * @param discountedPercent the discountedPercent to set
	 */
	public void setDiscountedPercent(int discountedPercent) {
		this.discountedPercent = discountedPercent;
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
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the size
	 */
	public Set<Size> getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Set<Size> size) {
		this.size = size;
	}
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the topLevelCategory
	 */
	public String getTopLevelCategory() {
		return topLevelCategory;
	}
	/**
	 * @param topLevelCategory the topLevelCategory to set
	 */
	public void setTopLevelCategory(String topLevelCategory) {
		this.topLevelCategory = topLevelCategory;
	}
	/**
	 * @return the secondLevelCategory
	 */
	public String getSecondLevelCategory() {
		return secondLevelCategory;
	}
	/**
	 * @param secondLevelCategory the secondLevelCategory to set
	 */
	public void setSecondLevelCategory(String secondLevelCategory) {
		this.secondLevelCategory = secondLevelCategory;
	}
	/**
	 * @return the thirdLevelCategory
	 */
	public String getThirdLevelCategory() {
		return thirdLevelCategory;
	}
	/**
	 * @param thirdLevelCategory the thirdLevelCategory to set
	 */
	public void setThirdLevelCategory(String thirdLevelCategory) {
		this.thirdLevelCategory = thirdLevelCategory;
	}
	
	
}
