package befaster.solutions.CHK;

import befaster.supermarket.StockKeepingUnit;

import java.util.ArrayList;
import java.util.List;

public class CheckoutSolution {

    List<StockKeepingUnit> skus = List.of(new StockKeepingUnit("", 50d));

    public Integer checkout(String skus) {
        System.out.println(skus);
        return -1;
    }
}

