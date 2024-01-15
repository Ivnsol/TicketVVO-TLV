package org.service;

import org.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class PriceAndMediana {

    public static void getPriceAndMediana(List<Flight> flights) {

        List<Integer> pricesForFlight = flights.stream()
                .map(Flight::getPrice)
                .collect(Collectors.toList());

        double averagePrice = pricesForFlight.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();

        double medianPrice = calculateMedian(pricesForFlight);

        double priceDifference = averagePrice - medianPrice;

        System.out.println("Разница между средней ценой и медианой для полета: " + priceDifference);
    }

    private static double calculateMedian(List<Integer> prices) {
        int size = prices.size();
        if (size % 2 == 0) {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            return prices.get(size / 2);
        }
    }
}

