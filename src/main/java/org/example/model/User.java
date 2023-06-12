package org.example.model;

import java.util.Date;

public class User {
    private int id;
    private String name;
    private String password;
    private Date createdAt;

    public User(int id, String name, String password, Date createdAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
    }

    public User(String name, String password, Date createdAt) {
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dueDate=" + createdAt +
                '}';
    }
}
