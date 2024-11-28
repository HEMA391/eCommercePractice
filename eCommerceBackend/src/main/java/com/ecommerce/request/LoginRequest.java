package com.ecommerce.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    // Default constructor
    public LoginRequest() {}

    // Constructor with email and password
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ToString method for easier logging or debugging
    @Override
    public String toString() {
        return "LoginRequest{email='" + email + "'}";
    }

    // Equals and HashCode methods (optional, for better object comparison)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LoginRequest that = (LoginRequest) obj;
        return email.equals(that.email) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return 31 * email.hashCode() + password.hashCode();
    }
}
