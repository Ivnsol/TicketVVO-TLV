package org.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.model.Flight;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static Flight createFlightFromJsonNode(JsonNode ticketNode) {
        return new Flight(
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
                ticketNode.get("price").asInt()
        );
    }

    public static List<Flight> readFlightsFromFile(String filePath) throws IOException {
        List<Flight> flights = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(fileInputStream);

            JsonNode ticketsNode = jsonNode.get("tickets");

            for (JsonNode ticketNode : ticketsNode) {
                Flight flight = createFlightFromJsonNode(ticketNode);
                if (flight.getOrigin().equals("VVO") && flight.getDestination().equals("TLV")) {
                    flights.add(flight);
                }
            }
        }

        return flights;
    }
}
