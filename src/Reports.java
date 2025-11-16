import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reports {

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

        list.sort((a,b) ->b.getValue() - a.getValue());

        System.out.println("=== Top Selling Items ===");
        int limit = Math.min(10, list.size());

        for(int i  = 0; i < limit; i++){
            var entry = list.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + "-" + entry.getValue() + " sold");
        }
    }
}
