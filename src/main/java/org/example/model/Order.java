package org.example.model;

public class Order {
    private int id;
    private int userId;
    private float total;

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public float getTotal() {
        return total;
    }

    public Order(int id, int userId, float total) {
        this.id = id;
        this.userId = userId;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", total=" + total +
                '}';
    }
}
