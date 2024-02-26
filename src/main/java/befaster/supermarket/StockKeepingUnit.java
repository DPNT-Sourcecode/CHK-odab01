package befaster.supermarket;

public class StockKeepingUnit {

    private String item;
    private double price;

    public StockKeepingUnit constructor(String item, double price) {
        StockKeepingUnit sku = new StockKeepingUnit();
        sku.item = item;
        sku.price = price;
        return sku;
    }

}

