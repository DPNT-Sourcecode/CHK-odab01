package befaster.solutions.CHK;

import befaster.supermarket.Offer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CheckoutSolution {

    Map<Character, Integer> stock = Map.ofEntries(
            Map.entry('A', 50),
            Map.entry('B', 30),
            Map.entry('C', 20),
            Map.entry('D', 15),
            Map.entry('E', 40),
            Map.entry('F', 10),
            Map.entry('G', 20),
            Map.entry('H', 10),
            Map.entry('I', 35),
            Map.entry('J', 60),
            Map.entry('K', 70), // changed
            Map.entry('L', 90),
            Map.entry('M', 15),
            Map.entry('N', 40),
            Map.entry('O', 10),
            Map.entry('P', 50),
            Map.entry('Q', 30),
            Map.entry('R', 50),
            Map.entry('S', 30),
            Map.entry('T', 20),
            Map.entry('U', 40),
            Map.entry('V', 50),
            Map.entry('W', 20),
            Map.entry('X', 90),
            Map.entry('Y', 10),
            Map.entry('Z', 50)
    );

//    // Offers are already sorted using a customised comparator to always favor the customer. Best offers are found first in the TreeSet.
//    Set<Offer> offers = new TreeSet<>(Set.of(
//            new Offer('A', 3, 130, null, false),
//            new Offer('A', 5, 200, null, false),
//            new Offer('B', 2, 45, null, false),
//            new Offer('E', 2, 80, 'B', true),
//            new Offer('F', 2, 20, 'F', true),
//            new Offer('H', 5, 45, null, false),
//            new Offer('H', 10, 80, null, true),
//            new Offer('K', 2, 150, null, true),
//            new Offer('N', 3, 120, 'M', true),
//            new Offer('P', 5, 200, null, true),
//            new Offer('Q', 3, 80, null, false),
//            new Offer('R', 3, 150, 'Q', true),
//            new Offer('U', 3, 120, 'U', true),
//            new Offer('V', 2, 90, null, false),
//            new Offer('V', 3, 130, null, true)
//    ));

    // Offers are already sorted using a customised comparator to always favor the customer. Best offers are found first in the TreeSet.
    Set<Offer> offers = new TreeSet<>(Set.of(
            new Offer('A', 3, 130, null, false),
            new Offer('A', 5, 200, null, false),
            new Offer('B', 2, 45, null, false),
            new Offer('E', 2, 80, 'B', true),
            new Offer('F', 2, 20, 'F', true),
            new Offer('H', 5, 45, null, false),
            new Offer('H', 10, 80, null, true),
            new Offer('K', 2, 120, null, true),
            new Offer('N', 3, 120, 'M', true),
            new Offer('P', 5, 200, null, true),
            new Offer('Q', 3, 80, null, false),
            new Offer('R', 3, 150, 'Q', true),
            new Offer('U', 3, 120, 'U', true),
            new Offer('V', 2, 90, null, false),
            new Offer('V', 3, 130, null, true)
    ));

    int bestResult;

    public Integer checkout(String skus) {

        bestResult = Integer.MAX_VALUE;
        Map<Character, Integer> basket = new HashMap<>();
        for (char character : skus.toCharArray()) {
            basket.put(character, basket.getOrDefault(character, 0) + 1);
        }
        TreeSet<Offer> filteredOffers = offers.stream().filter(it -> isOfferApplicable(basket, it)).collect(Collectors.toCollection(TreeSet::new));

        // Checking valid input. If not, exit the program -> -1
        if (invalidInput(basket)) {
            return -1;
        }

        // Recursively, try all different options to apply offers
        searchOptimalUseOfOffers(basket, 0, filteredOffers);

        return bestResult;
    }

    /**
     * @param basket - The basket with all the items the customer wants to buy
     * @return whether the input is valid or not. If not, we stop running and return -1
     */
    private boolean invalidInput(Map<Character, Integer> basket) {
        return !stock.keySet().containsAll(basket.keySet());
    }

    /**
     * Recursively, go through all different options until we find the best option for the customer to save money
     *
     * @param basket          - The current basket that the customer wants to buy
     * @param runningValue    - The current amount of the bill with the items that have been bought already
     * @param potentialOffers - TreeSet of the potential offers to be applied
     */
    private void searchOptimalUseOfOffers(Map<Character, Integer> basket, int runningValue, Set<Offer> potentialOffers) {
        int sum = runningValue;
        TreeSet<Offer> filteredOffers = potentialOffers.stream().filter(it -> isOfferApplicable(basket, it)).collect(Collectors.toCollection(TreeSet::new));
        if (!filteredOffers.isEmpty()) {
            Map<Character, Integer> updatedBasket = new HashMap<>(basket);
            sum += applyOffer(updatedBasket, filteredOffers.stream().findFirst().get());
            searchOptimalUseOfOffers(updatedBasket, sum, filteredOffers);
        }

        for (Map.Entry<Character, Integer> entry : basket.entrySet()) {
            sum += stock.get(entry.getKey()) * entry.getValue();
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
        int desiredAmount = basket.get(offer.item);
        int numOfUnitsPerOffer = offer.units;
        if (offer.freeItem == offer.item) {
            numOfUnitsPerOffer++;
        }
        int numTimesApplied = desiredAmount / numOfUnitsPerOffer;

        if (basket.get(offer.item) - (numTimesApplied * numOfUnitsPerOffer) <= 0) {
            basket.remove(offer.item);
        } else {
            basket.put(offer.item, basket.get(offer.item) - (numOfUnitsPerOffer * numTimesApplied));
        }

        if (basket.containsKey(offer.freeItem) && offer.freeItem != offer.item) {
            if (basket.get(offer.freeItem) - numTimesApplied <= 0) {
                basket.remove(offer.freeItem);
            } else {
                basket.put(offer.freeItem, basket.get(offer.freeItem) - numTimesApplied);
            }
        }

        return offer.price * numTimesApplied;
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

