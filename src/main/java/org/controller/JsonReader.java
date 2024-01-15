package org.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.model.Flight;

import java.io.File;
import java.util.*;

import static org.service.FasterFlights.getFasterFlights;
import static org.service.PriceAndMediana.getPriceAndMediana;


public class JsonReader {

    public static void main(String[] args) {
        try {
            String filePath = "tickets.json";

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

            JsonNode ticketsNode = jsonNode.get("tickets");

            List<Flight> flights = new ArrayList<>();

            for (JsonNode ticketNode : ticketsNode) {

                Flight flight = new Flight(
                        ticketNode.get("origin").asText(),
                        ticketNode.get("origin_name").asText(),
                        ticketNode.get("destination").asText(),
                        ticketNode.get("destination_name").asText(),
                        ticketNode.get("departure_date").asText(),
                        ticketNode.get("departure_time").asText(),
                        ticketNode.get("arrival_date").asText(),
                        ticketNode.get("arrival_time").asText(),
                        ticketNode.get("carrier").asText(),
                        ticketNode.get("stops").asInt(),
                        ticketNode.get("price").asInt());

                flights.add(flight);
            }

            getFasterFlights(flights);

            getPriceAndMediana(flights);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
