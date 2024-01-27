public class Order {
    private int userId;
    private String productName;
    private int quantity;
    private double totalSum;
    private Product product;

    public Order(int userId, String productName, int quantity, double totalSum, Product product) {
        this.userId = userId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalSum = totalSum;
        this.product = product;
    }

    public int getUserId() {
        return userId;
    }
    public String getProductName() {
        return productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalSum() {
        return totalSum;
    }
    public Product getProduct() {return product; }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }
}
