package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "cart_items")
	private Set<CartItem> cartItems = new HashSet<>();
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@Column(name = "total_item")
	private int totalItem;
	
	private int totalDiscountedPrice;
	
	private int discount;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(Long id, User user, Set<CartItem> cartItems, double totalPrice, int totalItem, int totalDiscountedPrice,
			int discount) {
		super();
		this.id = id;
		this.user = user;
		this.cartItems = cartItems;
		this.totalPrice = totalPrice;
		this.totalItem = totalItem;
		this.totalDiscountedPrice = totalDiscountedPrice;
		this.discount = discount;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the cartItems
	 */
	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	/**
	 * @param cartItems the cartItems to set
	 */
	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the totalItem
	 */
	public int getTotalItem() {
		return totalItem;
	}

	/**
	 * @param totalItem the totalItem to set
	 */
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	/**
	 * @return the totalDiscountedPrice
	 */
	public int getTotalDiscountedPrice() {
		return totalDiscountedPrice;
	}

	/**
	 * @param totalDiscountedPrice the totalDiscountedPrice to set
	 */
	public void setTotalDiscountedPrice(int totalDiscountedPrice) {
		this.totalDiscountedPrice = totalDiscountedPrice;
	}

	/**
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
