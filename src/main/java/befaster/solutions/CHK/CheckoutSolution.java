package befaster.solutions.CHK;

import befaster.supermarket.Offer;
import befaster.supermarket.StockKeepingUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckoutSolution {

    List<StockKeepingUnit> stock = List.of(
            new StockKeepingUnit('A', 50),
            new StockKeepingUnit('B', 30),
            new StockKeepingUnit('C', 20),
            new StockKeepingUnit('D', 15),
            new StockKeepingUnit('E', 40)
    );

    // Offers are already sorted using a customised comparator to always favor the customer. Best offers are found first in the TreeSet.
    List<Offer> offers = List.of(
            new Offer('A', 3, 130, null),
            new Offer('A', 5, 200, null),
            new Offer('B', 2, 45, null),
            new Offer('E', 2, 80, 'B')
    );

    int bestResult = Integer.MAX_VALUE;

    public Integer checkout(String skus) {

        Map<Character, Integer> basket = new HashMap<>();
        for (char character : skus.toCharArray()) {
            basket.put(character, basket.getOrDefault(character, 0) + 1);
        }

        // Checking valid input. If not, exit the program -> -1
        if (invalidInput(basket)) {
            return -1;
        }
        System.out.println("Before, Bestresult: " + bestResult);

        // Recursively, try all different options to apply offers
        searchOptimalUseOfOffers(basket, 0);

        System.out.println("After, Bestresult: " + bestResult);

        return bestResult;
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
     * Recursively, go through all different options until we find the best option for the customer to save money
     *
     * @param basket
     * @param runningValue
     * @return
     */
    private int searchOptimalUseOfOffers(Map<Character, Integer> basket, int runningValue) {
        System.out.println("RunningValue: " + runningValue);
        int sum = runningValue;
        Set<Offer> filteredOffers = offers.stream().filter(it -> isOfferApplicable(basket, it)).collect(Collectors.toSet());
        for (Offer offer : filteredOffers) {
            System.out.println("applying offer: " + offer.item);
            sum = runningValue;
            Map<Character, Integer> updatedBasket = new HashMap<>(basket);
            sum += applyOffer(updatedBasket, offer);
            searchOptimalUseOfOffers(updatedBasket, sum);
        }

        for (Map.Entry<Character, Integer> entry : basket.entrySet()) {
            System.out.println("Before, Sum is: " + sum);
            sum += (stock.stream().filter(it -> it.item == entry.getKey()).findFirst().get().price * entry.getValue());
            System.out.println("After, Sum is: " + sum);
        }
        if (sum < bestResult) {
            System.out.println("Updating best result value with " + sum);
            bestResult = sum;
        }

        return sum;
    }

    /**
     * Apply the offer passed as input parameter
     *
     * @param basket
     * @param offer
     * @return
     */
    private int applyOffer(Map<Character, Integer> basket, Offer offer) {
        if (basket.get(offer.item) - offer.units == 0) {
            basket.remove(offer.item);
        } else {
            basket.put(offer.item, basket.get(offer.item) - offer.units);
        }

        if (basket.containsKey(offer.freeItem)) {
            if (basket.get(offer.freeItem) - 1 == 0) {
                basket.remove(offer.freeItem);
            } else {
                basket.put(offer.freeItem, basket.get(offer.freeItem) - 1);
            }
        }

        return offer.price;
    }

    private boolean isOfferApplicable(Map<Character, Integer> basket, Offer offer) {
        if (basket.containsKey(offer.item) && basket.get(offer.item) >= offer.units) {
            if (offer.freeItem != null) {
                // The offer is only worth it in case the free item is already in the basket
                return basket.containsKey(offer.freeItem);
            } else {
                return true;
            }
        }
        return false;
    }
}

