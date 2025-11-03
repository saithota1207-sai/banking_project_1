package org.example.bankingsystem.model;

public class Admin {
    private int id;
    private String username;
    private String password;
    private String role;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {   // ✅ Add this
        return role;
    }

    public void setRole(String role) {  // ✅ And this
        this.role = role;
    }
}
