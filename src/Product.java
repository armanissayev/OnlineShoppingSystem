public class Product {
    private String Name;
    private int Price;
    private int Quantity;
    private String Description;


    public Product(String name, int price, int quantity, String description) {
        this.Name = name;
        this.Price = price;
        this.Quantity = quantity;
        this.Description = description;
    }

    public void setName(String name) {this.Name = name;}
    public void setPrice(int price) {this.Price = price;}
    public void setQuantity(int quantity) {this.Quantity = quantity;}
    public void setDescription(String description) {this.Description = description;}
}
