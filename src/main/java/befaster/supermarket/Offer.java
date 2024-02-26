package befaster.supermarket;

public class Offer {

    public Character item;
    public int units;
    public int price;
    public Character freeItem;

    public Offer(Character item, int units, int price, Character freeItem) {
        this.item = item;
        this.units = units;
        this.price = price;
        this.freeItem = freeItem;
    }


}
