package com.ecommerce.controller;

public class PaymentLinkResponse {

	private String payment_link_url;
	private String payment_link_id;
	
	public PaymentLinkResponse() {
		
	}

	/**
	 * @return the payment_link_url
	 */
	public String getPayment_link_url() {
		return payment_link_url;
	}

	/**
	 * @param payment_link_url the payment_link_url to set
	 */
	public void setPayment_link_url(String payment_link_url) {
		this.payment_link_url = payment_link_url;
	}

	/**
	 * @return the payment_link_id
	 */
	public String getPayment_link_id() {
		return payment_link_id;
	}

	/**
	 * @param payment_link_id the payment_link_id to set
	 */
	public void setPayment_link_id(String payment_link_id) {
		this.payment_link_id = payment_link_id;
	}

	public PaymentLinkResponse(String payment_link_url, String payment_link_id) {
		super();
		this.payment_link_url = payment_link_url;
		this.payment_link_id = payment_link_id;
	}
	
}
