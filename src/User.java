//import Order.java.Order;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private double balance;
    private ArrayList<Order> list = new ArrayList<Order>();

    public User() {
    }

    public User(int id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;

    }

    //getters and setters
    public double getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
