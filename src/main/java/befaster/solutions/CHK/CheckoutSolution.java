package befaster.solutions.CHK;

import befaster.supermarket.Offer;
import befaster.supermarket.StockKeepingUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    List<StockKeepingUnit> stock = List.of(
            new StockKeepingUnit('A', 50),
            new StockKeepingUnit('B', 30),
            new StockKeepingUnit('C', 20),
            new StockKeepingUnit('D', 15)
    );

    List<Offer> offers = List.of(
            new Offer('A', 3, 130),
            new Offer('B', 2, 45)
    );

    public Integer checkout(String skus) {
        Map<Character, Integer> basket = new HashMap<>();
        for (char character : skus.toCharArray()) {
            basket.put(character, basket.getOrDefault(character, 0) + 1);
        }

        int bill = applyOffers(basket);

        for (Map.Entry<Character, Integer> entry : basket.entrySet()) {
            bill += (stock.stream().filter(it -> it.item == entry.getKey()).findFirst().get().price * entry.getValue());
        }

        return bill;
    }

    private int applyOffers(Map<Character, Integer> basket) {
        boolean offerApplied = true;
        int sum = 0;
        while (offerApplied) {
            offerApplied = false;
            for (Offer offer : offers) {
                if (basket.containsKey(offer.item) && basket.get(offer.item) >= offer.units) {
                    offerApplied = true;
                    int numTimesApplied = basket.get(offer.item) / offer.units;
                    sum = numTimesApplied * offer.price;
                    if (basket.get(offer.item) % offer.units == 0) {
                        basket.remove(offer.item);
                    } else {
                        basket.put(offer.item, basket.get(offer.item) % offer.units);
                    }
                }
            }
        }
        return sum;
    }
}






