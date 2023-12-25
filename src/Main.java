import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Select action: ");
        System.out.println("1) To show products list");
        System.out.println("2) To add a product");
        System.out.println("3) To add a new user");
        System.out.println("4) To buy product");
        System.out.println("5) To return a product");
        System.out.println("6) To show all users");
        System.out.println("7) To show the certain user’s orders");
        
        int action = scan.nextInt();

        switch (action) {
            case 1:
                //Your code 1
                break;
            case 2:
                //Your code 2
                break;
            case 3:
                System.out.println("Enter your id: ");
                int id = scan.nextInt();

                String username;
                do {
                    System.out.println("Enter username: ");
                    username = scan.nextLine();
                } while (username.isEmpty());

                System.out.println("Enter your balance: ");
                double money = scan.nextDouble();

                User user = new User(id, username, money);

                System.out.println("Checking...");
                System.out.println("UserId: " + user.getId());
                System.out.println("Username: " + user.getName());
                System.out.println("Balance: " + user.getBalance());
                System.out.println("You have successfully signed in!");
                break;
            case 4:
                //Your code 4
                break;
            case 5:
                //Your code 5
                break;
            case 6:
                //Your code 6
                break;
            case 7:
                //Your code 7
                break;
            default:
                System.out.println("Unknown action");
                break;
        }
    }
}
