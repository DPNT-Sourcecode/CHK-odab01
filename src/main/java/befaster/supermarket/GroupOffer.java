package befaster.supermarket;

import java.util.Set;

public class GroupOffer {

    public Set<Character> items;
    public int units;
    public int price;

    public GroupOffer(Set<Character> items, int units, int price) {
        this.items = items;
        this.units = units;
        this.price = price;
    }
}
