import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    private static void printUsers(ArrayList<User> userList) {
        for (User value : userList) {
            int id = value.getId();
            String name = value.getName();
            double balance = value.getBalance();
            System.out.printf("%d - %s - %f\n", id, name, balance);
        }
        System.out.print("\n\n");
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        ArrayList<User> userList = new ArrayList<User>();
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
                    //Your code 1
                    break;
                case 2:
                    System.out.print("Enter the name of the product: ");
                    String name = "";
                    do {
                        name = scan.nextLine();
                    } while(name.isEmpty());

                    System.out.print("Enter the price of new product: ");
                    double price = scan.nextInt();

                    break;
                case 3:
                    String username;
                    System.out.print("Enter username: ");
                    do {
                        username = scan.nextLine();
                    } while (username.isEmpty());

                    System.out.print("Enter your balance: ");
                    double money = scan.nextDouble();

                    User user = new User(++ count, username, money);
                    userList.add(user);

                    System.out.println("Checking...");
                    System.out.println("The new user added successfully!\n\n");
                    break;
                case 4:
                    //Your code 4
                    break;
                case 5:
                    //Your code 5
                    break;
                case 6:
                    printUsers(userList);
                    break;
                case 7:
                    //Your code 7
                    break;
                case 8:
                    System.out.println("Exit in progress...");
                    System.exit(0);
                default:
                    System.out.println("Unknown action!");
            }
            System.out.print("Continue? [Y/N]\n");
            String res = scan.nextLine();
            do {
                res = scan.nextLine();
            } while (res.isEmpty());
            if (!Objects.equals(res, "Y")) break;
        }
    }
}
