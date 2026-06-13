import java.util.*;

// Class containing methods related to food ordering
class Food {

    // Method to place the order
    void placeOrder(String food) {
        System.out.println("\nOrder Placed Successfully!");
        System.out.println(food + " has been ordered.");
    }

    // Method to calculate and display the bill
    void Bill(String food, int price, int quantity) {
        int total = price * quantity;

        System.out.println("\n--------- ORDER SUMMARY ---------");
        System.out.println("Food Name : " + food);
        System.out.println("Price     : " + price);
        System.out.println("Quantity  : " + quantity);
        System.out.println("Total Bill: " + total);
    }

    // Method to display delivery message
    void order() {
        System.out.println("\nYour order is on the way...");
    }
}

// Main class
public class OnlineFoodDeliverySystem {

    public static void main(String args[]) {

        // Creating Scanner object for input
        Scanner sc = new Scanner(System.in);

        // Creating object of Food class
        Food a = new Food();

        // Welcome Screen
        System.out.println("****************************************");
        System.out.println("         WELCOME TO FOODIE HUB");
        System.out.println("****************************************");

        // Login Section
        System.out.println("\n------------ LOGIN ------------");

        System.out.print("Enter Username: ");
        String username = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        // Login Successful Message
        System.out.println("\nLogin Successful!");
        System.out.println("Welcome, " + username);

        // Displaying Food Menu
        System.out.println("\n------------ MENU ------------");
        System.out.println("1. Pizza - 250");
        System.out.println("2. Burger - 90");
        System.out.println("3. Noodles - 90");
        System.out.println("4. American Chopsey - 250");
        System.out.println("5. Kukure Momos - 150");
        System.out.println("6. Tandoori Momos - 250");

        // Taking user's choice
        System.out.print("\nEnter Your Choice (1-6): ");
        int choice = sc.nextInt();

        // Variables to store food name and price
        String food = "";
        int price = 0;

        // Assigning food name and fixed price
        switch (choice) {

            case 1:
                food = "Pizza";
                price = 250;
                break;

            case 2:
                food = "Burger";
                price = 90;
                break;

            case 3:
                food = "Noodles";
                price = 90;
                break;

            case 4:
                food = "American Chopsey";
                price = 250;
                break;

            case 5:
                food = "Kukure Momos";
                price = 150;
                break;

            case 6:
                food = "Tandoori Momos";
                price = 250;
                break;

            // If user enters an invalid choice
            default:
                System.out.println("Invalid Choice!");
                return;
        }

        // Taking quantity from user
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        // Confirming the order
        System.out.println("\nConfirm Your Order");
        System.out.println("1. Yes");
        System.out.println("2. No");

        System.out.print("Enter Choice: ");
        int confirm = sc.nextInt();

        // Checking confirmation
        if (confirm == 1) {

            // Calling methods
            a.placeOrder(food);
            a.Bill(food, price, quantity);
            a.order();

            // Thank You Message
            System.out.println("\n********************************");
            System.out.println("Thank You for Ordering!");
            System.out.println("Visit Again!");
            System.out.println("********************************");

        } else {

            // Order cancellation message
            System.out.println("\nOrder Cancelled.");
        }
    }
}