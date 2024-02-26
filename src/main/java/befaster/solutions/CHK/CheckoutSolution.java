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

    // Offers are already sorted using a customised comparator to always favor the customer. Best offers are found first in the TreeSet.
    Set<Offer> offers = new TreeSet<>(Set.of(
            new Offer('A', 3, 130, null, 20),
            new Offer('A', 5, 200, null, 50),
            new Offer('B', 2, 45, null, 15),
            new Offer('E', 2, 80, 'B', 30)
    ));

    int bestResult = Integer.MAX_VALUE;

    public Integer checkout(String skus) {

//        Comparator<Offer> comparator = getOfferComparator();

        Map<Character, Integer> basket = new HashMap<>();
        for (char character : skus.toCharArray()) {
            basket.put(character, basket.getOrDefault(character, 0) + 1);
        }

        // Checking valid input. If not, exit the program -> -1
        if (invalidInput(basket)) {
            return -1;
        }

        // First, we try to apply as much offers as possible
        searchOptimalUseOfOffers(basket);

        // Then, we calculate the total bill with the remaining elements in the basket
//        for (Map.Entry<Character, Integer> entry : basket.entrySet()) {
//            bill += (stock.stream().filter(it -> it.item == entry.getKey()).findFirst().get().price * entry.getValue());
//        }

        return bestResult;
    }

//    private Comparator<Offer> getOfferComparator(String skus, Map<Character, Integer> basket) {
//        return (offer1, offer2) -> {
//            int totalPrizeOffer1 = offer1.units * offer1.price + ();
//            int totalPrizeOffer2 = 0;
//
//            double totalDiscountOffer1 = offer1.totalDiscount * (basket.getOrDefault(offer1.item, 0) / offer1.units);
//
//            return Double.compare(offer2.totalDiscount / offer2.units, offer1.totalDiscount / offer1.units);
//        };
//    }

    /**
     * Check if the input is valid. Otherwise, we stop running and return -1
     *
     * @param basket
     * @return
     */
    private boolean invalidInput(Map<Character, Integer> basket) {
        return !stock.stream().map(it -> it.item).collect(Collectors.toSet()).containsAll(basket.keySet());
    }

    private int searchOptimalUseOfOffers(Map<Character, Integer> basket) {
        int sum = 0;
        Set<Offer> filteredOffers = offers.stream().filter(it -> isOfferApplicable(basket, it)).collect(Collectors.toSet());
        for (Offer offer : filteredOffers) {
            Map<Character, Integer> updatedBasket = new HashMap<>(basket);
            sum = applyOffer(updatedBasket, offer);
            sum += searchOptimalUseOfOffers(updatedBasket);
            if(sum < bestResult){
                bestResult = sum;
            }
        }

        for (Map.Entry<Character, Integer> entry : basket.entrySet()) {
            sum += (stock.stream().filter(it -> it.item == entry.getKey()).findFirst().get().price * entry.getValue());
        }

        return sum;
    }

    private int applyOffer(Map<Character, Integer> basket, Offer offer) {
        if (basket.get(offer.item) - offer.units == 0) {
            basket.remove(offer.item);
        } else {
            basket.put(offer.item, basket.get(offer.item) - offer.units);
        }

        if (basket.get(offer.freeItem) - 1 == 0) {
            basket.remove(offer.freeItem);
        } else {
            basket.put(offer.freeItem, basket.get(offer.freeItem) - 1);
        }

        return offer.price;
    }


    /**
     * Apply offers until none of them could be applied to the remaining elements in the basket
     *
     * @param basket
     * @return
     */
//    private int applyOffers(Map<Character, Integer> basket) {
//        boolean offerApplied = true;
//        int sum = 0;
//        while (offerApplied) {
//            offerApplied = false;
    // Offers are already sorted by relevance due to a customised comparator so we always favor the customer
//            for (Offer offer : offers) {
//                if (isOfferApplicable(basket, offer)) {
//                    offerApplied = true;
//                    int numTimesApplied = basket.get(offer.item) / offer.units;
//                    sum += numTimesApplied * offer.price;
//                    if (basket.get(offer.item) % offer.units == 0) {
//                        basket.remove(offer.item);
//                    } else {
//                        basket.put(offer.item, basket.get(offer.item) % offer.units);
//                    }
//                }
//            }
//        }
//        return sum;
//    }
    private boolean isOfferApplicable(Map<Character, Integer> basket, Offer offer) {
        if (basket.containsKey(offer.item) && basket.get(offer.item) >= offer.units) {
            if (offer.freeItem != null) {
                return true;
            } else {
                // The offer is only worth it in case the free item is already in the basket
                return basket.containsKey(offer.freeItem);
            }
        }
        return false;
    }
}
