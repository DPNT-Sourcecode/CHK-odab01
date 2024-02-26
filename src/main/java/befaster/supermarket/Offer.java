package befaster.supermarket;

import java.util.List;

public class Offer implements Comparable<Offer> {

    public Character item;
    public int units;
    public int price;
    public List<Character> freeItems;
    public double totalDiscount;

    public Offer(Character item, int units, int price, List<Character> freeItems, double totalDiscount) {
        this.item = item;
        this.units = units;
        this.price = price;
        this.freeItems = freeItems;
        this.totalDiscount = totalDiscount;
    }

    @Override
    public int compareTo(Offer otherOffer) {
        int itemComparison = this.item.compareTo(otherOffer.item);
        if (itemComparison != 0) {
            return itemComparison;
        }

        // If items are equal, order by the discount we get per item
        return Double.compare(otherOffer.totalDiscount / otherOffer.units, this.totalDiscount / this.units);
    }
}
