package com.library.models;

public class User {
    private String id;         // Used as the unique login ID
    private String name;       // User's full name
    private String password;   // Hashed password (never store plain text!)
    private String phone;

    // Default constructor (needed for JDBC or frameworks)
    public User() {
    }

    // Constructor with all fields
    public User(String id, String name, String phone, String password) {
        this.id = id;
        this.name = name;
        this.phone=phone;
        this.password = password;
    }

    // Getter for ID (used in DB lookups and session tracking)
    public String getId() {
        return id;
    }

    // Setter for ID (used during registration)
    public void setId(String id) {
        this.id = id;
    }

    // Getter for name (used to display welcome messages, etc.)
    public String getName() {
        return name;
    }

    // Setter for name (set during registration or updates)
    public void setName(String name) {
        this.name = name;
    }

    // Getter for phone
    public String getPhone() {
        return phone;
    }

    // Setter for name (set during registration or updates)
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter for password (used internally only to compare hashes)
    public String getPassword() {
        return password;
    }

    // Setter for password (used to save hashed password during registration)
    public void setPassword(String password) {
        this.password = password;
    }
}

