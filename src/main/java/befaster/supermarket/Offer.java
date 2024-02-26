package befaster.supermarket;

import java.util.List;

public class Offer {

    public Character item;
    public int units;
    public int price;
    public List<Character> freeItems;

    public Offer(Character item, int units, int price, List<Character> freeItems) {
        this.item = item;
        this.units = units;
        this.price = price;
        this.freeItems = freeItems;
    }

}

