import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reports {

    private static class CustomerTotal {
        Customer customer;
        float totalSpent;

        CustomerTotal(Customer customer, float totalSpent) {
            this.customer = customer;
            this.totalSpent = totalSpent;
        }
    }

    // 1) Top selling items
    public static void topSellingItemsReport(ArrayList<Customer> customers) {

        HashMap<String, Integer> salesMap = new HashMap<>();

        for (Customer customer : customers) {
            for (Purchase purchase : customer.getPurchases()) {
                String itemName = purchase.getItem().getName();
                int quantity = purchase.getQuantity();

                salesMap.put(itemName, salesMap.getOrDefault(itemName, 0) + quantity);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(salesMap.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("=== Top Selling Items ===");
        int limit = Math.min(10, list.size());

        ArrayList<String> lines = new ArrayList<>();
        lines.add("Top Selling Items Report");
        lines.add("------------------------");
        lines.add("Rank | Item Name | Quantity Sold");

        for (int i = 0; i < limit; i++) {
            var entry = list.get(i);
            String line = (i + 1) + ". " + entry.getKey() + " - " + entry.getValue() + " sold";
            System.out.println(line);
            lines.add(line);
        }

        ReportFileWriter.writeReport("TopSellingItemsReport.txt", lines);
    }

    // 2) Top customers by total spent
    public static void topCustomersReport(ArrayList<Customer> customers) {

        ArrayList<CustomerTotal> totals = new ArrayList<>();

        for (Customer customer : customers) {
            float totalSpent = 0;

            for (Purchase purchase : customer.getPurchases()) {
                totalSpent += purchase.getItem().getPrice() * purchase.getQuantity();
            }

            totals.add(new CustomerTotal(customer, totalSpent));
        }

        totals.sort((a, b) -> Float.compare(b.totalSpent, a.totalSpent));

        System.out.println("=== Top 10 Customers by Total Spent ===");
        int limit = Math.min(10, totals.size());

        ArrayList<String> lines = new ArrayList<>();
        lines.add("Top Customers by Total Spent");
        lines.add("--------------------------------");
        lines.add("Rank | Customer Name | Total Spent");

        for (int i = 0; i < limit; i++) {
            CustomerTotal entry = totals.get(i);

            String line = (i + 1) + ". " +
                    entry.customer.getName() +
                    " - spent $" + entry.totalSpent;
            System.out.println(line);
            lines.add(line);
        }

        ReportFileWriter.writeReport("TopCustomersReport.txt", lines);
    }

    // 3) Category sales report
    public static void categorySalesReport(ArrayList<Customer> customers) {

        HashMap<String, Float> categoryTotals = new HashMap<>();

        for (Customer customer : customers) {
            for (Purchase purchase : customer.getPurchases()) {
                Item item = purchase.getItem();
                String category = item.getCategory();
                float saleAmount = item.getPrice() * purchase.getQuantity();

                categoryTotals.put(category,
                        categoryTotals.getOrDefault(category, 0f) + saleAmount);
            }
        }

        List<Map.Entry<String, Float>> list = new ArrayList<>(categoryTotals.entrySet());
        list.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));

        System.out.println("=== Category Sales Report ===");

        ArrayList<String> lines = new ArrayList<>();
        lines.add("Category Sales Report");
        lines.add("---------------------");
        lines.add("Category | Total Sales");

        for (var entry : list) {
            String line = entry.getKey() + " — $" + entry.getValue();
            System.out.println(line);
            lines.add(line);
        }

        ReportFileWriter.writeReport("CategorySalesReport.txt", lines);
    }

    // 4) Customer purchase history (item + total spent, plus file)
    public static void customerPurchaseHistoryReport(ArrayList<Customer> customers, String customerName) {

        Customer target = null;
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                target = customer;
                break;
            }
        }

        if (target == null) {
            System.out.println("No customer with name " + customerName + " found");
            return;
        }

        System.out.println("=== Customer Purchase History for " + customerName + " ===");

        if (target.getPurchases().isEmpty()) {
            System.out.println("No Purchase Found");
            return;
        }

        HashMap<String, Float> history = new HashMap<>();

        for (Purchase purchase : target.getPurchases()) {
            Item item = purchase.getItem();
            String itemName = item.getName();
            float totalAmount = item.getPrice() * purchase.getQuantity();

            history.put(itemName, history.getOrDefault(itemName, 0f) + totalAmount);
        }

        ArrayList<String> lines = new ArrayList<>();
        lines.add("Purchase History for: " + target.getName());
        lines.add("-------------------------------------------");
        lines.add("Item Name | Total Spent");
        lines.add("-------------------------------------------");

        for (var entry : history.entrySet()) {
            String line = entry.getKey() + " - $" + entry.getValue();
            System.out.println(line);
            lines.add(line);
        }

        String fileName = target.getName().replace(" ", "_") + "_PurchaseHistory.txt";
        ReportFileWriter.writeReport(fileName, lines);
    }

    // 5) Inventory report
    public static void inventoryReport(ArrayList<Item> items) {

        ArrayList<Item> sorted = new ArrayList<>(items);
        sorted.sort((a, b) -> b.getQuantity() - a.getQuantity());

        System.out.println("=== Inventory Report (Sorted by Quantity Descending) ===");

        ArrayList<String> lines = new ArrayList<>();
        lines.add("Inventory Report");
        lines.add("----------------");
        lines.add("Item Name | Quantity | Category");

        for (Item item : sorted) {
            String line = item.getName() +
                    " | " + item.getQuantity() +
                    " | " + item.getCategory();
            System.out.println(line);
            lines.add(line);
        }

        ReportFileWriter.writeReport("InventoryReport.txt", lines);
    }
}




