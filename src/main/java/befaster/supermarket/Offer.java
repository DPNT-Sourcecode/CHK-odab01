package befaster.supermarket;

public class Offer {

    private StockKeepingUnit sku;
    private int units;
    private double price;

    public Offer(StockKeepingUnit sku, int units, double price) {
        this.sku = sku;
        this.units = units;
        this.price = price;
    }

}

