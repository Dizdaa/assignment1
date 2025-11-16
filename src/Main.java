import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Item> Items = CSVLoader.loadItems("items.csv");
        ArrayList<Customer> customers = CSVLoader.loadcustomers("customers.csv");

        CSVLoader.loadPurchases("purchases.csv", customers, Items);

        Reports.topSellingItemsReport(customers);
}
}
