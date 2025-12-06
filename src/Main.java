import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 1) Učitavanje podataka iz CSV fajlova
        ArrayList<Item> items = CSVLoader.loadItems("items.csv");
        ArrayList<Customer> customers = CSVLoader.loadcustomers("customers.csv");
        CSVLoader.loadPurchases("purchases.csv", customers, items);

        // 2) Meni za odabir reporta
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println();
            System.out.println("=== E-Commerce Reports Menu ===");
            System.out.println("1 - Top Selling Items Report");
            System.out.println("2 - Top Customers Report");
            System.out.println("3 - Category Sales Report");
            System.out.println("4 - Customer Purchase History Report");
            System.out.println("5 - Inventory Report");
            System.out.println("0 - Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Top 10 selling items
                    Reports.topSellingItemsReport(customers);
                    break;

                case 2:
                    // Top 10 customers by total spent
                    Reports.topCustomersReport(customers);
                    break;

                case 3:
                    // Category sales report
                    Reports.categorySalesReport(customers);
                    break;

                case 4:
                    // Customer purchase history + fajl
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    Reports.customerPurchaseHistoryReport(customers, customerName);
                    break;

                case 5:
                    // Inventory report
                    Reports.inventoryReport(items);
                    break;

                case 0:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

        scanner.close();
    }
}

