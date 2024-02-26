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
            new Offer("A", 3, 130d),
            new Offer("B", 2, 45d)
    );

    public Integer checkout(String skus) {
        Map<Character, Integer> basket = new HashMap<>();
        int bill = 0;
        for (char character : skus.toCharArray()) {
            basket.put(character, basket.getOrDefault(character, 0) + 1);
        }

        while (anyOfferApplicable()) {

        }


        return bill;
    }

    private int anyOfferApplicable(Map<Character, Integer> basket) {
        boolean offerApplied = true;
        int sum = 0;
        while (offerApplied) {
            offers.stream().forEach(offer -> {
                        if (basket.containsKey(offer.item) && basket.get(offer.item) >= offer.units) {
                            int numTimesApplied = basket.get(offer.item) / offer.units;
                            sum = numTimesApplied * offer.price;
                            if (basket.get(offer.item) % offer.units == 0) {
                                basket.remove(offer.item);
                            } else {
                                basket.put(offer.item, basket.get(offer.item) % offer.units);
                            }
                        }
                    }
            );
        }

    }
}

