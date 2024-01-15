package org.service;

import org.model.Flight;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FasterFlights {
    public static void getFasterFlights(List<Flight> tickets) {
        Set<Flight> uniqueFlights = new HashSet<>(tickets.stream()
                .collect(Collectors.toMap(Flight::getCarrier, Function.identity(), (existing, replacement) ->
                        existing.getDuration().compareTo(replacement.getDuration()) < 0 ? existing : replacement))
                .values());

        for (Flight flight : uniqueFlights) {
            System.out.println(
                    "Cамый быстрый маршрут для компании - " + flight.getCarrier() +
                            ", займет время в пути - " + formatDuration(flight.getDuration())
            );
        }
    }

    private static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return String.format("%02d:%02d", hours, minutes);
    }
}
