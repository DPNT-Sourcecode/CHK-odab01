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
            new StockKeepingUnit('E', 40),
            new StockKeepingUnit('F', 10),
            new StockKeepingUnit('G', 20),
            new StockKeepingUnit('H', 10),
            new StockKeepingUnit('I', 35),
            new StockKeepingUnit('J', 60),
            new StockKeepingUnit('K', 80),
            new StockKeepingUnit('L', 90),
            new StockKeepingUnit('M', 15),
            new StockKeepingUnit('N', 40),
            new StockKeepingUnit('O', 10),
            new StockKeepingUnit('P', 50),
            new StockKeepingUnit('Q', 30),
            new StockKeepingUnit('R', 50),
            new StockKeepingUnit('S', 30),
            new StockKeepingUnit('T', 20),
            new StockKeepingUnit('U', 40),
            new StockKeepingUnit('V', 50),
            new StockKeepingUnit('W', 20),
            new StockKeepingUnit('X', 90),
            new StockKeepingUnit('Y', 10),
            new StockKeepingUnit('Z', 50)
    );

    List<Offer> offers = List.of(
            new Offer('A', 3, 130, null),
            new Offer('A', 5, 200, null),
            new Offer('B', 2, 45, null),
            new Offer('E', 2, 80, 'B'),
            new Offer('F', 2, 20, 'F'),
            new Offer('H', 5, 45, null),
            new Offer('H', 10, 80, null),
            new Offer('K', 2, 150, null),
            new Offer('N', 3, 120, 'M'),
            new Offer('P', 5, 200, null),
            new Offer('Q', 3, 80, null),
            new Offer('R', 3, 150, 'Q'),
            new Offer('U', 3, 120, 'U'),
            new Offer('V', 2, 90, null),
            new Offer('V', 3, 130, null)
    );

    int bestResult;

    public Integer checkout(String skus) {

        bestResult = Integer.MAX_VALUE;
        Map<Character, Integer> basket = new HashMap<>();
        for (char character : skus.toCharArray()) {
            basket.put(character, basket.getOrDefault(character, 0) + 1);
        }

        // Checking valid input. If not, exit the program -> -1
        if (invalidInput(basket)) {
            return -1;
        }

        // Recursively, try all different options to apply offers
        searchOptimalUseOfOffers(basket, 0);

        return bestResult;
    }

    /**
     * @param basket - The basket with all the items the customer wants to buy
     * @return whether the input is valid or not. If not, we stop running and return -1
     */
    private boolean invalidInput(Map<Character, Integer> basket) {
        return !stock.stream().map(it -> it.item).collect(Collectors.toSet()).containsAll(basket.keySet());
    }

    /**
     * Recursively, go through all different options until we find the best option for the customer to save money
     *
     * @param basket       - The current basket that the customer wants to buy
     * @param runningValue - The current amout of the bill with the items that have been bought already
     */
    private void searchOptimalUseOfOffers(Map<Character, Integer> basket, int runningValue) {
        int sum = runningValue;
        Set<Offer> filteredOffers = offers.stream().filter(it -> isOfferApplicable(basket, it)).collect(Collectors.toSet());
        for (Offer offer : filteredOffers) {
            sum = runningValue;
            Map<Character, Integer> updatedBasket = new HashMap<>(basket);
            sum += applyOffer(updatedBasket, offer);
            searchOptimalUseOfOffers(updatedBasket, sum);
        }

        for (Map.Entry<Character, Integer> entry : basket.entrySet()) {
            sum += (stock.stream().filter(it -> it.item == entry.getKey()).findFirst().get().price * entry.getValue());
        }
        if (sum < bestResult) {
            bestResult = sum;
        }
    }

    /**
     * Apply the offer passed as input parameter
     *
     * @param basket - The current basket content after all the previous temporal operations have been applied
     * @param offer  - the offer to apply
     * @return the cost after the offer has been applied
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

    /**
     * @param basket - The current basket content after all the previous temporal operations have been applied
     * @param offer  - The offer to check
     * @return a boolean indicating wheter the offer can be applied or not
     */
    private boolean isOfferApplicable(Map<Character, Integer> basket, Offer offer) {
        int numberOfItemsRequired = offer.units;
        if (offer.freeItem == offer.item) {
            numberOfItemsRequired++;
        }
        if (basket.containsKey(offer.item) && basket.get(offer.item) >= numberOfItemsRequired) {
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




