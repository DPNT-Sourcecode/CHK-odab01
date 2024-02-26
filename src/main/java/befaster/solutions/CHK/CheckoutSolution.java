package befaster.solutions.CHK;

import befaster.supermarket.Offer;
import befaster.supermarket.StockKeepingUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    List<StockKeepingUnit> skus = List.of(
            new StockKeepingUnit("A", 50d),
            new StockKeepingUnit("B", 30d),
            new StockKeepingUnit("C", 20d),
            new StockKeepingUnit("D", 15d)
    );

    List<Offer> offers = List.of(
            new Offer(new StockKeepingUnit("A", 50d), 3, 130d),
            new Offer(new StockKeepingUnit("B", 50d), 2, 45d)
    );

    public Integer checkout(String skus) {
        Map<String, Integer> basket = new HashMap<>();
        

        System.out.println(skus);

        return -1;
    }
}


