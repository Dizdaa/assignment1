public class Purchase {
    private Item item;
    private int  quantity;


    public Purchase(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    public Item getItem(){
        return this.item;
    }
    public int getQuantity(){
        return this.quantity;
    }
    @Override
    public String toString(){
        return "Purchase{" +
                "item=" + item.getName() +
                ", quantity=" + quantity +
                '}';
    }
}
