package com.ecommerce.model;

public class PaymentDetails {

//	private PaymentMethod paymentMethod;
//	private PaymentStatus status;
	private String paymentMethod;
	private String status;
	private String paymentId;
	private String razorpayPaymentLinkId;
	private String razorpayPaymentLinkReferenceId;
	private String razorpayPaymentLinkStatus;
	private String razorpayPaymentId;
	
	public PaymentDetails() {
		// TODO Auto-generated constructor stub
	}

	public PaymentDetails(String paymentMethod, String status, String paymentId, String razorpayPaymentLinkId,
			String razorpayPaymentLinkReferenceId, String razorpayPaymentLinkStatus, String razorpayPaymentId) {
		super();
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.paymentId = paymentId;
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
		this.razorpayPaymentId = razorpayPaymentId;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the paymentId
	 */
	public String getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the razorpayPaymentLinkId
	 */
	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}

	/**
	 * @param razorpayPaymentLinkId the razorpayPaymentLinkId to set
	 */
	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}

	/**
	 * @return the razorpayPaymentLinkReferenceId
	 */
	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}

	/**
	 * @param razorpayPaymentLinkReferenceId the razorpayPaymentLinkReferenceId to set
	 */
	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}

	/**
	 * @return the razorpayPaymentLinkStatus
	 */
	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}

	/**
	 * @param razorpayPaymentLinkStatus the razorpayPaymentLinkStatus to set
	 */
	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}

	/**
	 * @return the razorpayPaymentId
	 */
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	/**
	 * @param razorpayPaymentId the razorpayPaymentId to set
	 */
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	
}
