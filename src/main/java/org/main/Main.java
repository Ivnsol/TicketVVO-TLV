package org.main;

import org.model.Flight;

import java.util.*;

import static org.service.FasterFlights.getFasterFlights;
import static org.Controller.JsonReader.readFlightsFromFile;
import static org.service.PriceAndMediana.getPriceAndMediana;

public class Main {
    public static void main(String[] args) {
        String filePath = "tickets.json";
        try {

            List<Flight> flights = readFlightsFromFile(filePath);

            getFasterFlights(flights);

            getPriceAndMediana(flights);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
