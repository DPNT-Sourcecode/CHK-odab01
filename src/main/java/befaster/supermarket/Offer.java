package befaster.supermarket;

import java.util.Set;

public class Offer implements Comparable<Offer> {

    public Character item;
    public int units;
    public int price;
    public Character freeItem;
    public boolean isolated;
    public Set<Character> items;

    public Offer(Character item, int units, int price, Character freeItem, boolean isolated, Set<Character> items) {
        this.item = item;
        this.units = units;
        this.price = price;
        this.freeItem = freeItem;
        this.isolated = isolated;
        this.items = items;
    }

    @Override
    public int compareTo(Offer otherOffer) {
        if (this.isolated && !otherOffer.isolated) {
            return -1;
        } else if (!this.isolated && otherOffer.isolated) {
            return 1;
        } else {
            int unitComparison = Integer.compare(otherOffer.units, this.units);
            if (unitComparison != 0) {
                return unitComparison;
            }
            return Integer.compare(this.item, otherOffer.item);
        }
    }
}
