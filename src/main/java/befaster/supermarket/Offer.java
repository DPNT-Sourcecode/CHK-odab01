package befaster.supermarket;

public class Offer implements Comparable<Offer> {

    public Character item;
    public int units;
    public int price;
    public Character freeItem;
    public double totalDiscount;

    public Offer(Character item, int units, int price, Character freeItem, double totalDiscount) {
        this.item = item;
        this.units = units;
        this.price = price;
        this.freeItem = freeItem;
        this.totalDiscount = totalDiscount;
    }

    @Override
    public int compareTo(Offer otherOffer) {
        // Order by the discount we get per item. A good indicator of which offer is better.
        return Double.compare(otherOffer.totalDiscount / otherOffer.units, this.totalDiscount / this.units);
    }
}
