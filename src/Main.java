import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    private static void printUsers(ArrayList<User> userList) {
        System.out.println("--------------------------------------------------");
        for (User value : userList) {
            int id = (int) value.getId();
            String name = value.getName();
            double balance = value.getBalance();
            System.out.printf("%d - %s - %f\n", id, name, balance);
        }
        System.out.println("--------------------------------------------------");
        System.out.print("\n");
    }

    private static void printProducts(ArrayList<Product> products) {
        System.out.println("--------------------------------------------------");
        for (Product value : products) {
            int id = value.getId();
            String name = value.getName();
            double price = value.getPrice();
            int quantity = value.getQuantity();
            String description = value.getDescription();
            System.out.printf("%d - %s - %f - %d:\n%s\n", id, name, price, quantity, description);
        }
        System.out.print("\n");
    }

    private static void printOrders(ArrayList<Order> orders) {
        System.out.println("--------------------------------------------------");
        for (Order value : orders) {
            int id = value.getUserId();
            String name = value.getProductName();
            double sum = value.getTotalSum();
            int quantity = value.getQuantity();
            System.out.printf("%d - %s - %f - %d:\n", id, name, sum, quantity);
        }
        System.out.println("--------------------------------------------------");
        System.out.print("\n");
    }

    public static void main(String[] args) throws FileNotFoundException {
//        File myObj = new File("src/input.txt");
        Scanner scan = new Scanner(System.in);
        int countUserId = 0;
        int countProductId = 0;
        ArrayList<User> userList = new ArrayList<User>();
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Order> orders = new ArrayList<Order>();
        ArrayList<Integer> primary = new ArrayList<Integer>();
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Select action: ");
            System.out.println("1) To show products list");
            System.out.println("2) To add a product");
            System.out.println("3) To add a new user");
            System.out.println("4) To buy product");
            System.out.println("5) To return a product");
            System.out.println("6) To show all users");
            System.out.println("7) To show the certain user’s orders");
            System.out.println("8) To subscribe to premium");
            System.out.println("9) To exit");

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
                    } while (name.isEmpty());

                    System.out.print("Enter the price of new product: ");
                    double price = scan.nextDouble();
                    System.out.print("Enter the amount of products: ");
                    int quantity = scan.nextInt();
                    System.out.print("Input the description of the product in one line: ");
                    String description = "";
                    while (description.isEmpty()) {
                        description = scan.nextLine();
                    }
                    Product product = new Product(++countProductId, name, price, quantity, description);
                    System.out.printf("ID of this product is %d\n", product.getId());
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
                    System.out.print("The new user added successfully!\n");
                    System.out.printf("Your id is %d\n\n", user.getId());
                    break;
                case 4:
                    System.out.print("Enter your id: ");
                    int userId = scan.nextInt();
                    User currentUser = findUserById(userList, userId);
                    while (currentUser == null) {
                        System.out.print("User with such ID not found. Enter your id again: ");
                        userId = scan.nextInt();
                        currentUser = findUserById(userList, userId);
                    }

                    printProducts(products);

                    System.out.println("User: " + currentUser.getName() + ", Balance: " + currentUser.getBalance());
                    System.out.println("--------------------------------------------------");

                    System.out.println("Enter the product ID: ");
                    int productId = scan.nextInt();

                    Product selectedProduct = findProductById(products, productId);

                    //if the product is found

                    while (selectedProduct == null) {
                        System.out.println("Enter the product ID: ");
                        productId = scan.nextInt();

                        selectedProduct = findProductById(products, productId);
                    }

                    System.out.println("Product selected: " + selectedProduct.getName());
                    System.out.println("Enter quantity to buy: ");
                    int quantityToBuy = scan.nextInt();

                    if (quantityToBuy <= selectedProduct.getQuantity()) {
                        double totalCost = selectedProduct.getTotalCost(quantityToBuy);
                        totalCost = currentUser.calculate(totalCost);

                        if (totalCost <= currentUser.getBalance()) {
                            selectedProduct.setQuantity(selectedProduct.getQuantity() - quantityToBuy);
                            currentUser.deductBalance(totalCost);

                            Order order = new Order((int) currentUser.getId(), selectedProduct.getName(), quantityToBuy, totalCost, selectedProduct);
                            currentUser.addOrder(order);
                            orders.add(order);

                            System.out.println("Purchase successful! Total cost: " + totalCost);
                        } else {
                            System.out.println("Not enough balance to make the purchase.");
                        }
                    } else {
                        System.out.println("Not enough stock available for the selected product.");
                    }
                    break;
                case 5:
                    orders = returnProduct(userList, products, orders, scan);
                    break;
                case 6:
                    printUsers(userList);
                    break;
                case 7:
                    System.out.print("Enter your id: ");
                    userId = scan.nextInt();
                    currentUser = findUserById(userList, userId);
                    while (currentUser == null) {
                        System.out.print("User with such ID not found. Enter your id again: ");
                        userId = scan.nextInt();
                        userId--;
                        currentUser = findUserById(userList, userId);
                    }
                    printOrders(currentUser.getOrders());
                    break;
                case 8:
                    double premiumPrice = 10000;
                    System.out.print("Enter your id: ");
                    int user_id = scan.nextInt();

                    User userToUpgrade = findUserById(userList, user_id);
                    while (userToUpgrade == null) {
                        System.out.print("User with such ID not found. Enter your id again: ");
                        user_id = scan.nextInt();
                        userToUpgrade = findUserById(userList, user_id);
                    }
                    System.out.println("User selected: " + userToUpgrade.getName());
                    System.out.println("Premium cost: " + premiumPrice);

                    if (premiumPrice <= userToUpgrade.getBalance()) {
                        userToUpgrade.setBalance(userToUpgrade.getBalance() - premiumPrice);



                        for (int i = 0; i < userList.size(); ++ i) {
                            User cur = userList.get(i);
                            if (cur == userToUpgrade) {
                                PremiumUser premiumUser = new PremiumUser(userToUpgrade.getId(), userToUpgrade.getName(), userToUpgrade.getBalance());
                                userList.set(i, premiumUser);
                                break;
                            }
                        }

                        System.out.println("User upgraded to Premium!");
                    } else {
                        System.out.println("Not enough balance to make the purchase.");
                    }
                    break;

                case 9:
                    System.out.println("Exit in progress...");
                    System.exit(0);
                default:
                    System.out.println("Unknown action!");
            }
            System.out.print("Continue? [Y/N]\n");
            String res = "";
            while (!Objects.equals(res, "Y") && !Objects.equals(res, "N")) {
                res = scan.nextLine();
            }
            if (!Objects.equals(res, "Y")) {
                System.out.println("Exit in progress...");
                break;
            }
            System.out.println("--------------------------------------------------");
        }
    }


    private static ArrayList<Order> returnProduct(ArrayList<User> userList, ArrayList<Product> products, ArrayList<Order> orders, Scanner scan) {
        int userId = 0;
        System.out.print("Enter your ID: ");
        userId = scan.nextInt();
        User user = findUserById(userList, userId);
        while(user == null) {
            System.out.print("Enter your ID again: ");
            userId = scan.nextInt();
            user = findUserById(userList, userId);
        }

        System.out.println("User: " + user.getName() + ", Balance: " + user.getBalance());

        System.out.print("Enter the product ID to return: ");
        int productId = scan.nextInt();

        Product selectedProduct = findProductById(products, productId);
        while(selectedProduct == null) {
            System.out.print("Not Found! Enter the product ID to return: ");
            productId = scan.nextInt();
            selectedProduct = findProductById(products, productId);
        }

        System.out.println("Product selected for return: " + selectedProduct.getName());
        System.out.print("Enter quantity to return: ");
        int quantityToReturn = scan.nextInt();

        // Increase the quantity in ArrayList<Product> products
        selectedProduct.setQuantity(selectedProduct.getQuantity() + quantityToReturn);
        for (int i = 0; i < orders.size(); ++ i) {
            Order order = orders.get(i);
            if (order.getProductName() == selectedProduct.getName()) {
                int cur = order.getQuantity();
                order.setQuantity(cur - quantityToReturn);
                double sum = order.getTotalSum();
                order.setTotalSum(sum - quantityToReturn * order.getProduct().getPrice());
                orders.set(i, order);
            }
        }
        // Adjust user balance or handle refunds if necessary
        double cost = selectedProduct.getTotalCost(quantityToReturn);
        cost = user.calculate(cost);
        user.returnBalance(cost);

        System.out.println("Return successful! Quantity returned: " + quantityToReturn);
        return orders;
    }

    public static Product findProductById(ArrayList<Product> products, int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    private static User findUserById(ArrayList<User> userList, int userId) {
        for (User user : userList) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
}
