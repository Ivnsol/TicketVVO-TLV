package org.model;

import lombok.Data;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

    public Flight(String origin, String originName, String destination, String destinationName,
                  String departureDate, String departureTime, String arrivalDate, String arrivalTime,
                  String carrier, int stops, int price) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.departureTime = parseVvoTime(departureDate + " " + departureTime);
        this.arrivalTime = parseTlvTime(arrivalDate + " " + arrivalTime);
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
        this.duration = calculateDuration();
    }

    private Duration calculateDuration() {
        return Duration.between(departureTime, arrivalTime);
    }

    private ZonedDateTime parseVvoTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
        return ZonedDateTime.of(localDateTime, ZoneId.of("UTC+10:00"));
    }

    private ZonedDateTime parseTlvTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
        return ZonedDateTime.of(localDateTime, ZoneId.of("UTC+02:00"));
    }
}
