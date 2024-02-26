package befaster.supermarket;

import java.util.List;

public class Offer implements Comparable<Offer> {

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

    @Override
    public int compareTo(Offer otherOffer) {
        int itemComparison = this.item.compareTo(otherOffer.item);
        if (itemComparison != 0) {
            return itemComparison;
        }

        // If items are equal, order by number of items in the offer
        return Integer.compare(otherOffer.units, this.units);
    }
}



