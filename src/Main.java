import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    private static void printUsers(ArrayList<User> userList) {
        for (User value : userList) {
            int id = (int) value.getId();
            String name = value.getName();
            double balance = value.getBalance();
            System.out.printf("%d - %s - %f\n", id, name, balance);
        }
        System.out.print("\n\n");
    }
    private static void printProducts(ArrayList<Product> products) {
        for (Product value : products) {
            int id = value.getId();
            String name = value.getName();
            double price = value.getPrice();
            int quantity = value.getQuantity();
            String description = value.getDescription();
            System.out.printf("%d - %s - %f - %d:\n%s", id, name, price, quantity, description);
        }
        System.out.print("\n\n");
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int countUserId = 0;
        int countProductId = 0;
        ArrayList<User> userList = new ArrayList<User>();
        ArrayList<Product> products = new ArrayList<Product>();
        while (true) {
            System.out.println("Select action: ");
            System.out.println("1) To show products list");
            System.out.println("2) To add a product");
            System.out.println("3) To add a new user");
            System.out.println("4) To buy product");
            System.out.println("5) To return a product");
            System.out.println("6) To show all users");
            System.out.println("7) To show the certain user’s orders");
            System.out.println("8) To exit");

            System.out.print("Choice: ");
            int action = scan.nextInt();

            switch (action) {
                case 1:
                    printProducts(products);
                    break;
                case 2:
                    System.out.print("Enter the name of the product: ");
                    String name = "";
                    do {
                        name = scan.nextLine();
                    } while(name.isEmpty());

                    System.out.print("Enter the price of new product: ");
                    double price = scan.nextDouble();
                    System.out.print("Enter the amount of products: ");
                    int quantity = scan.nextInt();
                    System.out.print("Input the description of the product in one line: ");
                    String description = "";
                    while(description.isEmpty()) {
                        description = scan.nextLine();
                    }
                    Product product = new Product(++countProductId, name, price, quantity, description);
                    products.add(product);
                    break;
                case 3:
                    String username = "";
                    System.out.print("Enter username: ");
                    while (username.isEmpty()) {
                        username = scan.nextLine();
                    }

                    System.out.print("Enter your balance: ");
                    double money = scan.nextDouble();

                    User user = new User(++countUserId, username, money);
                    userList.add(user);

                    System.out.println("Checking...");
                    System.out.println("The new user added successfully!\n\n");
                    break;
                case 4:
                    User currentUser = userList.get(userList.size() - 1);

                    System.out.println("User: " + currentUser.getName() + ", Balance: " + currentUser.getBalance());

                    System.out.println("Enter the product ID: ");
                    int productId = scan.nextInt();

                    Product selectedProduct = null;
                    for (Product prod: products) {
                        if (prod.getId() == productId) {
                            selectedProduct = prod;
                            break;
                        }
                    }

                    //if the product is found
                    if (selectedProduct != null) {
                        System.out.println("Product selected: " + selectedProduct.getName());
                        System.out.println("Enter quantity to buy: ");
                        int quantityToBuy = scan.nextInt();

                        if (quantityToBuy <= selectedProduct.getQuantity()) {
                            double totalCost = selectedProduct.getTotalCost(quantityToBuy);

                            if (totalCost <= currentUser.getBalance()) {
                                selectedProduct.setQuantity(selectedProduct.getQuantity() - quantityToBuy);
                                currentUser.deductBalance(totalCost);

                                Order order = new Order((int)currentUser.getId(), selectedProduct.getName(), quantityToBuy, totalCost);
                                currentUser.addOrder(order);

                                System.out.println("Purchase successful! Total cost: " + totalCost);
                            } else {
                                System.out.println("Not enough balance to make the purchase.");
                            }
                        } else {
                            System.out.println("Not enough stock available for the selected product.");
                        }
                    } else {
                        System.out.println("Product not found with the given ID.");
                    }
                    break;
                case 5:
                    returnProduct(userList, products, scan);
                    break;
                case 6:
                    printUsers(userList);
                    break;
                case 7:
                    viewLastOrders(userList);
                    break;
                case 8:
                    System.out.println("Exit in progress...");
                    System.exit(0);
                default:
                    System.out.println("Unknown action!");
            }
            System.out.print("Continue? [Y/N]\n");
            String res = "";
            while (res.isEmpty()) {
                res = scan.nextLine();
            }
            if (!Objects.equals(res, "Y")) {
                System.out.println("Exit in progress...");
                break;
            }
        }
    }

    private static void returnProduct(ArrayList<User> userList, ArrayList<Product> products, Scanner scan) {
        if (!userList.isEmpty()) {
            User lastUser = userList.get(userList.size() - 1);

            System.out.println("User: " + lastUser.getName() + ", Balance: " + lastUser.getBalance());

            System.out.print("Enter the product ID to return: ");
            int productId = scan.nextInt();

            Product selectedProduct = findProductById(products, productId);

            if (selectedProduct != null) {
                System.out.println("Product selected for return: " + selectedProduct.getName());
                System.out.print("Enter quantity to return: ");
                int quantityToReturn = scan.nextInt();

                // Increase the quantity in ArrayList<Product> products
                selectedProduct.setQuantity(selectedProduct.getQuantity() + quantityToReturn);

                // Adjust user balance or handle refunds if necessary
                lastUser.returnBalance(selectedProduct.getTotalCost(quantityToReturn));

                System.out.println("Return successful! Quantity returned: " + quantityToReturn);
            } else {
                System.out.println("Product not found with the given ID.");
            }
        } else {
            System.out.println("No users available.");
        }
    }

    public static Product findProductById(ArrayList<Product> products, int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static void viewLastOrders(ArrayList<User> userList) {
        User lastUser = userList.get(userList.size() - 1);
        System.out.println("Last User's Orders:");
        for (Order order : lastUser.getOrders()) {
            System.out.println("Product: " + order.getProductName() +
                    ", Quantity: " + order.getQuantity() +
                    ", Total Cost: " + order.calculateTotalCost());
        }
    }
}
