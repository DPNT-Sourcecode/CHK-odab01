package befaster.solutions.CHK;

import befaster.supermarket.Offer;
import befaster.supermarket.StockKeepingUnit;

import java.util.*;
import java.util.stream.Collectors;

public class CheckoutSolution {

    List<StockKeepingUnit> stock = List.of(
            new StockKeepingUnit('A', 50),
            new StockKeepingUnit('B', 30),
            new StockKeepingUnit('C', 20),
            new StockKeepingUnit('D', 15)
    );

    Set<Offer> offers = new TreeSet<>(Set.of(
            new Offer('A', 3, 130, new ArrayList<>()),
            new Offer('A', 5, 200, new ArrayList<>()),
            new Offer('B', 2, 45, new ArrayList<>()),
            new Offer('E', 2, 80, List.of('B'))
    ));

    public Integer checkout(String skus) {

        Map<Character, Integer> basket = new HashMap<>();
        for (char character : skus.toCharArray()) {
            basket.put(character, basket.getOrDefault(character, 0) + 1);
        }

        // Checking valid input. If not, exit the program -> -1
        if (invalidInput(basket)) {
            return -1;
        }

        // First, we try to apply as much offers as possible
        int bill = applyOffers(basket);

        // Then, we calculate the total bill with the remaining elements in the basket
        for (Map.Entry<Character, Integer> entry : basket.entrySet()) {
            bill += (stock.stream().filter(it -> it.item == entry.getKey()).findFirst().get().price * entry.getValue());
        }

        return bill;
    }

    /**
     * Check if the input is valid. Otherwise, we stop running and return -1
     *
     * @param basket
     * @return
     */
    private boolean invalidInput(Map<Character, Integer> basket) {
        return !stock.stream().map(it -> it.item).collect(Collectors.toSet()).containsAll(basket.keySet());
    }

    /**
     * Apply offers until none of them could be applied to the remaining elements in the basket
     *
     * @param basket
     * @return
     */
    private int applyOffers(Map<Character, Integer> basket) {
        boolean offerApplied = true;
        int sum = 0;
        while (offerApplied) {
            offerApplied = false;
            for (Offer offer : offers) {
                if (basket.containsKey(offer.item) && basket.get(offer.item) >= offer.units) {
                    offerApplied = true;
                    int numTimesApplied = basket.get(offer.item) / offer.units;
                    sum += numTimesApplied * offer.price;
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


