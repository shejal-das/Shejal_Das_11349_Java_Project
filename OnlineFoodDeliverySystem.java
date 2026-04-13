import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class FoodItem {
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - Rs." + (int) price + "/-";
    }
}

class Restaurant {
    private String name;
    private ArrayList<FoodItem> menu;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
    }

    public void addFoodItem(FoodItem item) {
        menu.add(item);
    }

    public String getName() {
        return name;
    }

    public ArrayList<FoodItem> getMenu() {
        return menu;
    }

    public void displayMenu() {
        System.out.println("\n  --- Menu of " + name + " ---");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + menu.get(i));
        }
    }
}

class User {
    private String username;
    private String password;

    private static HashMap<String, String> userDatabase = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static boolean register(String username, String password) {
        if (userDatabase.containsKey(username)) {
            System.out.println("  [!] Username already exists. Try logging in.");
            return false;
        }
        userDatabase.put(username, password);
        System.out.println("  [✓] Registration successful! Welcome, " + username + "!");
        return true;
    }

    public static String login(String username, String password) {
        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            System.out.println("  [✓] Login successful! Welcome back, " + username + "!");
            return username;
        }
        System.out.println("  [!] Invalid username or password.");
        return null;
    }

    public String getUsername() {
        return username;
    }
}

class Cart {
    private ArrayList<FoodItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        items.add(item);
        System.out.println("  [✓] " + item.getName() + " added to cart.");
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            System.out.println("  [✓] " + items.get(index).getName() + " removed from cart.");
            items.remove(index);
        } else {
            System.out.println("  [!] Invalid item number.");
        }
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("  Your cart is empty.");
            return;
        }
        System.out.println("\n  --- Your Cart ---");
        for (int i = 0; i < items.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + items.get(i));
        }
        System.out.println("  Total: Rs." + (int) calculateTotal() + "/-");
    }

    public double calculateTotal() {
        double total = 0;
        for (FoodItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}

class Order {
    private String username;
    private ArrayList<FoodItem> orderedItems;
    private double totalBill;

    public Order(String username, Cart cart) {
        this.username = username;
        this.orderedItems = new ArrayList<>(cart.getItems());
        this.totalBill = cart.calculateTotal();
    }

    public void generateBill() {
        System.out.println("\n  ========================================");
        System.out.println("         ORDER CONFIRMATION BILL         ");
        System.out.println("  ========================================");
        System.out.println("  Customer : " + username);
        System.out.println("  ----------------------------------------");
        for (FoodItem item : orderedItems) {
            System.out.println("  " + item.getName() + "  .....  Rs." + (int) item.getPrice() + "/-");
        }
        System.out.println("  ----------------------------------------");
        System.out.println("  TOTAL BILL  :  Rs." + (int) totalBill + "/-");
        System.out.println("  ========================================");
        System.out.println("  Order Placed Successfully!");
        System.out.println("  !! CONTINUE EATING !!!");
        System.out.println("  ========================================\n");
    }
}

public class OnlineFoodDeliverySystem {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Restaurant r1 = new Restaurant("Spice Garden");
        r1.addFoodItem(new FoodItem("Pizza", 299));
        r1.addFoodItem(new FoodItem("Burger", 199));
        r1.addFoodItem(new FoodItem("Pasta", 350));
        r1.addFoodItem(new FoodItem("Garlic Bread", 120));

        Restaurant r2 = new Restaurant("Biryani House");
        r2.addFoodItem(new FoodItem("Chicken Biryani", 249));
        r2.addFoodItem(new FoodItem("Veg Biryani", 179));
        r2.addFoodItem(new FoodItem("Raita", 49));
        r2.addFoodItem(new FoodItem("Shahi Tukda", 99));

        Restaurant r3 = new Restaurant("Snack Zone");
        r3.addFoodItem(new FoodItem("Samosa (2 pcs)", 30));
        r3.addFoodItem(new FoodItem("Pav Bhaji", 120));
        r3.addFoodItem(new FoodItem("Cold Coffee", 80));
        r3.addFoodItem(new FoodItem("French Fries", 99));

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);

        System.out.println("\n  ========================================");
        System.out.println("   Welcome to Online Food Delivery System ");
        System.out.println("  ========================================");

        String loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n  1. Login");
            System.out.println("  2. Register");
            System.out.print("  Enter Choice: ");
            int authChoice = Integer.parseInt(sc.nextLine().trim());

            System.out.print("  Enter Username: ");
            String username = sc.nextLine().trim();
            System.out.print("  Enter Password: ");
            String password = sc.nextLine().trim();

            if (authChoice == 1) {
                loggedInUser = User.login(username, password);
            } else if (authChoice == 2) {
                boolean registered = User.register(username, password);
                if (registered) {
                    loggedInUser = username;
                }
            } else {
                System.out.println("  [!] Invalid choice.");
            }
        }

        Cart cart = new Cart();
        boolean running = true;

        while (running) {
            System.out.println("\n  ----------------------------------------");
            System.out.println("  MAIN MENU");
            System.out.println("  ----------------------------------------");
            System.out.println("  1. Browse Restaurants & Order Food");
            System.out.println("  2. View Cart");
            System.out.println("  3. Modify Cart (Remove Item)");
            System.out.println("  4. Place Order");
            System.out.println("  5. Logout / Exit");
            System.out.print("  Enter Choice: ");
            int mainChoice = Integer.parseInt(sc.nextLine().trim());

            switch (mainChoice) {

                case 1:
                    System.out.println("\n  --- Available Restaurants ---");
                    for (int i = 0; i < restaurants.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + restaurants.get(i).getName());
                    }
                    System.out.print("  Select Restaurant (number): ");
                    int restChoice = Integer.parseInt(sc.nextLine().trim()) - 1;

                    if (restChoice < 0 || restChoice >= restaurants.size()) {
                        System.out.println("  [!] Invalid restaurant choice.");
                        break;
                    }

                    Restaurant selectedRest = restaurants.get(restChoice);
                    selectedRest.displayMenu();

                    boolean ordering = true;
                    while (ordering) {
                        System.out.print("\n  Enter item number to add (0 to go back): ");
                        int itemChoice = Integer.parseInt(sc.nextLine().trim()) - 1;
                        if (itemChoice == -1) {
                            ordering = false;
                        } else if (itemChoice >= 0 && itemChoice < selectedRest.getMenu().size()) {
                            cart.addItem(selectedRest.getMenu().get(itemChoice));
                        } else {
                            System.out.println("  [!] Invalid item number.");
                        }
                    }
                    break;

                case 2:
                    cart.viewCart();
                    break;

                case 3:
                    cart.viewCart();
                    if (!cart.isEmpty()) {
                        System.out.print("  Enter item number to remove (0 to cancel): ");
                        int removeChoice = Integer.parseInt(sc.nextLine().trim()) - 1;
                        if (removeChoice >= 0) {
                            cart.removeItem(removeChoice);
                        }
                    }
                    break;

                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("  [!] Your cart is empty. Add items before placing order.");
                    } else {
                        cart.viewCart();
                        System.out.print("\n  Confirm order? (yes/no): ");
                        String confirm = sc.nextLine().trim().toLowerCase();
                        if (confirm.equals("yes")) {
                            Order order = new Order(loggedInUser, cart);
                            order.generateBill();
                            cart.clear();
                        } else {
                            System.out.println("  [!] Order cancelled.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n  Thank you for using Online Food Delivery System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("  [!] Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}