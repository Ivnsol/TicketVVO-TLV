package org.model;

import lombok.Data;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Flight {
    private final String origin;
    private final String originName;
    private final String destination;
    private final String destinationName;
    private final ZonedDateTime departureTime;
    private final ZonedDateTime arrivalTime;
    private final String carrier;
    private final int stops;
    private final int price;

    private final Duration duration;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");


    public Flight(String origin, String originName, String destination, String destinationName,
                  String departureDate, String departureTime, String arrivalDate, String arrivalTime,
                  String carrier, int stops, int price) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.departureTime = parseTimeWithZone(departureDate + " "
                + departureTime, ZoneId.of("Asia/Vladivostok"));
        this.arrivalTime = parseTimeWithZone(arrivalDate + " "
                + arrivalTime, ZoneId.of("Asia/Tel_Aviv"));
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
        this.duration = calculateDuration();
    }

    private Duration calculateDuration() {
        return Duration.between(departureTime, arrivalTime);
    }

    private ZonedDateTime parseTimeWithZone(String dateTimeString, ZoneId zoneId) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
        return ZonedDateTime.of(localDateTime, zoneId);
    }
}
