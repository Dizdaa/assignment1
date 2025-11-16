import java.util.ArrayList;

public class Customer {
    private String name;
    private String email;
    private ArrayList<Purchase> purchases;

    public Customer(String name, String email){
        this.name = name;
        this.email = email;
        this.purchases = new ArrayList<>();
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public ArrayList<Purchase> getPurchases(){
        return this.purchases;
    }
    public void addPurchase(Purchase purchase){
        purchases.add(purchase);
    }
@Override
    public String toString(){
        return "Customer{" +
                ", name='" +  name + '\'' +
                ", email=" + email + '\'' +
                ", totalPurchases=" + purchases.size() +
                '}';
}

}
