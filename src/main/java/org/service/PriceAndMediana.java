package org.service;

import org.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class PriceAndMediana {

    public static void getPriceAndMediana(List<Flight> flights) {

        List<Integer> prices = flights.stream()
                .map(Flight::getPrice)
                .collect(Collectors.toList());

        double averagePrice = prices.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();

        double medianPrice;
        int size = prices.size();
        if (size % 2 == 0) {
            medianPrice = (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            medianPrice = prices.get(size / 2);
        }

        double priceDifference = averagePrice - medianPrice;

        System.out.println("Разница между средней ценой  и медианой для полета: " + priceDifference);
    }
}

