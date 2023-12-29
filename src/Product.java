public class Product {
    private int id;
    private String Name;
    private double Price;
    private int Quantity;
    private String Description;


    public Product(int id, String name, double price, int quantity, String description) {
        this.id = id;
        this.Name = name;
        this.Price = price;
        this.Quantity = quantity;
        this.Description = description;
    }

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.Name = name;}
    public void setPrice(double price) {this.Price = price;}
    public void setQuantity(int quantity) {this.Quantity = quantity;}
    public void setDescription(String description) {this.Description = description;}

    public int getId() {return id;}
    public String getName() {return Name;}
    public double getPrice() {return Price;}
    public int getQuantity() {return Quantity;}
    public String getDescription() {return Description;}

    public double getTotalCost(int quantity) {
        return Price * quantity;
    }
}
