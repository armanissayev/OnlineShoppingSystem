import java.util.ArrayList;

public class User {
    //name, balance, ArrayList of orders
    private int id;
    private String name;
    private double balance;
    private ArrayList<Order> orders = new ArrayList<Order>();

    public User() {

    }
    public User(int id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.orders = new ArrayList<>();
    }

    //getters and setters
    public double getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public  String getName() {
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

    public ArrayList<Order> getOrders() {return orders;}

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void deductBalance(double totalCost) {
        balance -= totalCost;
    }

    public void returnBalance(double amount) {
        // Assuming you have a method to set the balance in your User class
        setBalance(getBalance() + amount);
    }
}
