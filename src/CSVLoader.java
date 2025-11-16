import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

public class CSVLoader {
    public static ArrayList<Item> loadItems(String items) {
        ArrayList<Item> Items = new ArrayList<>();

        try {
            InputStream is = CSVLoader.class.getClassLoader().getResourceAsStream(items);

            if (is == null) {
                System.out.println("File not found in resources:" + Items);
                return Items;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String name = data[0];
                float price = Float.parseFloat(data[1]);
                int quantity = Integer.parseInt(data[2]);
                String category = data[3];

                Items.add(new Item(name, price, quantity, category));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading CSV file: " + Items);
            e.printStackTrace();
        }
        return Items;
    }
    public static ArrayList<Customer> loadcustomers(String Customer)  {
        ArrayList<Customer>customerList = new ArrayList<>();

        try{
            InputStream is = CSVLoader.class.getClassLoader().getResourceAsStream(Customer);

            if(is == null){
                System.out.println("File not found in resources:" + Customer);
                return customerList;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            br.readLine();

            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String email = data[1];

                customerList.add(new Customer(name, email));
            }
            br.close();
        } catch(Exception e){
            System.out.println("Error reading CSV file: " + Customer);
            e.printStackTrace();
            return customerList;
        }
        return customerList;
    }
    public static void loadPurchases(String purchases, ArrayList<Customer> customers, ArrayList<Item> items){
        try{
            InputStream is = CSVLoader.class.getClassLoader().getResourceAsStream(purchases);

            if(is ==null){
                System.out.println("File not found in resources:" + purchases);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();


            while((line = br.readLine()) != null){
                String[] data = line.split(",");

                String customerName = data[0];
                String itemName = data[1];
                int quantity = Integer.parseInt(data[2]);


                Customer customer = null;
                for(Customer c : customers){
                    if(c.getName().equalsIgnoreCase(customerName)){
                        customer = c;
                        break;
                    }
                }
                if(customer == null){
                    System.out.println("Customer with name " + customerName + " does not exist");
                    continue;
                }

                Item foundItem = null;
                for(Item item : items){
                    if(item.getName().equalsIgnoreCase(itemName)){
                        foundItem = item;
                        break;
                    }
                }
                if(foundItem == null){
                    System.out.println("Item not found: " + itemName);
                    continue;
                }
                Purchase purchase = new Purchase(foundItem, quantity);

                customer.addPurchase(purchase);

                foundItem.setQuantity(foundItem.getQuantity() - quantity);
            }
            br.close();

        } catch (Exception e){
            System.out.println("Error reading CSV file: " + purchases);
            e.printStackTrace();
        }
    }
}
