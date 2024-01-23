import java.util.ArrayList;

public class OnlineShop {
    private ArrayList<PremiumUser> premiumUsers;

    public OnlineShop() {
        premiumUsers = new ArrayList<>();
    }

    public void addPremiumUser(PremiumUser premiumUser) {
        premiumUsers.add(premiumUser);
        System.out.println("Premium user added: " + premiumUser.getName());
    }

    public ArrayList<PremiumUser> getPremiumUsers() {
        return premiumUsers;
    }
}
