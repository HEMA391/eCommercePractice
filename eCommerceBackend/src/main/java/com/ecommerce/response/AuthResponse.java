package com.ecommerce.response;

import org.springframework.http.HttpStatus;

public class AuthResponse {

    private String status;  // Represents the status of the request (e.g., "success", "failure")
    private String message; // Descriptive message (e.g., "Signup Failed", "Email already exists")
    //private HttpStatus code; // HTTP status code (e.g., 200 for success, 400 for bad request)

    // Default constructor
    public AuthResponse() {
        // No-arg constructor
    }

    // Parameterized constructor for setting status, message, and HTTP status code
    public AuthResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }
}
