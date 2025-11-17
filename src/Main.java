import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Item> Items = CSVLoader.loadItems("items.csv");
        ArrayList<Customer> customers = CSVLoader.loadcustomers("customers.csv");

        CSVLoader.loadPurchases("purchases.csv", customers, Items);

        Reports.topSellingItemsReport(customers);

        Reports.topCustomersReport(customers);

        Reports.categorySalesReport(customers);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        Reports.customerPurchaseHistoryReport(customers, name);

        Reports.inventoryReport(Items);

}
}
