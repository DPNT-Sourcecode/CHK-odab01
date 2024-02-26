package befaster.supermarket;

public class Offer implements Comparable<Offer>{

    public Character item;
    public int units;
    public int price;
    public Character freeItem;
    public boolean isolated;

    public Offer(Character item, int units, int price, Character freeItem, boolean isolated) {
        this.item = item;
        this.units = units;
        this.price = price;
        this.freeItem = freeItem;
        this.isolated = isolated;
    }

    @Override
    public int compareTo(Offer otherOffer) {
        if(this.isolated && !otherOffer.isolated){
            return -1;
        } else if(!this.isolated && otherOffer.isolated){
            return 1;
        }

        // Order by the discount we get per item. A good indicator of which offer is better.
        return Double.compare(otherOffer.totalDiscount / otherOffer.units, this.totalDiscount / this.units);
    }
}


