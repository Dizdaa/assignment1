import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reports {

    private static class CustomerTotal{
        Customer customer;
        float totalSpent;
        CustomerTotal(Customer customer, float totalSpent){
            this.customer = customer;
            this.totalSpent = totalSpent;
        }
    }

    public static void topSellingItemsReport(ArrayList<Customer> customers){

        HashMap< String, Integer> salesMap = new HashMap<>();

        for (Customer customer : customers){
            for(Purchase purchase : customer.getPurchases()){
                String itemName = purchase.getItem().getName();
                int quantity = purchase.getQuantity();

                salesMap.put(itemName, salesMap.getOrDefault(itemName, 0) + quantity);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(salesMap.entrySet());

        list.sort((a,b) -> b.getValue() - a.getValue());

        System.out.println("=== Top Selling Items ===");
        int limit = Math.min(10, list.size());

        for(int i  = 0; i < limit; i++){
            var entry = list.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + "-" + entry.getValue() + " sold");
        }
    }


    public static void topCustomersReport(ArrayList<Customer> customers){

        ArrayList<CustomerTotal> totals = new ArrayList<>();

        for(Customer customer : customers){
            float totalSpent = 0;

            for(Purchase purchase :customer.getPurchases()){
                totalSpent += purchase.getItem().getPrice() * purchase.getQuantity();
            }

            totals.add(new CustomerTotal(customer, totalSpent));

        }
        totals.sort((a, b) -> Float.compare(b.totalSpent, a.totalSpent));
        System.out.println("=== Top 10 Customers by total spent ===");
        int limit = Math.min(10, totals.size());

        for(int i = 0; i< limit; i++){
            CustomerTotal entry = totals.get(i);

            System.out.println((i + 1) + ". " +
                    entry.customer.getName() +
                    " - spent $" + entry.totalSpent);
        }
    }
    public static void categorySalesReport(ArrayList<Customer> customers){

        HashMap<String, Float>categoryTotals = new HashMap<>();


        for(Customer customer : customers){
            for(Purchase purchase :customer.getPurchases()){
                Item item = purchase.getItem();
                String category = item.getCategory();
                float saleAmount = item.getPrice() * purchase.getQuantity();

                categoryTotals.put(category,categoryTotals.getOrDefault(category,0f) + saleAmount);
            }
        }
        List<Map.Entry<String,Float>> list = new ArrayList<>(categoryTotals.entrySet());

        list.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));

        System.out.println("=== Category Sales Report ===");

        for(Map.Entry<String,Float> entry : list){
            System.out.println(entry.getKey() + " - $" + entry.getValue());
        }
    }

    public static void customerPurchaseHistoryReport(ArrayList<Customer> customers, String customerName){

        Customer target = null;
        for(Customer customer : customers){
            if(customer.getName().equalsIgnoreCase(customerName)){
                target = customer;
                break;
            }
        }
        if(target == null){
            System.out.println("No customer with name " + customerName + " found");
            return;
        }
        System.out.println("=== Customer Purchase History for " +customerName + " ===");

        if(target.getPurchases().isEmpty()){
            System.out.println("No Purchase Found");
            return;
        }

        HashMap<String, Float> history = new HashMap<>();

        for(Purchase purchase: target.getPurchases()){
            Item item = purchase.getItem();
            String itemName = item.getName();
            float totalAmount = item.getPrice() * purchase.getQuantity();

            history.put(itemName, history.getOrDefault(itemName, 0f) +  totalAmount);
        }
        for(var entry : history.entrySet()){
            System.out.println(entry.getKey() + " - $" + entry.getValue());
        }
    }
    public static void inventoryReport(ArrayList<Item> items){

        ArrayList<Item> sorted = new ArrayList<>(items);

        sorted.sort((a, b) -> b.getQuantity() - a.getQuantity());

        System.out.println("=== Inventory Report (Sorted by Quantity Descending) ===");

        for(Item item :  sorted){
            System.out.println(item.getName() + " - Quantity in Stock:" + item.getQuantity() + " - Category: "+item.getCategory());
        }
    }
}


