package com.amadeus.flightSearchApi.service.impl;

import com.amadeus.flightSearchApi.entity.Flight;
import com.amadeus.flightSearchApi.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HttpRequestService {
    private final FlightDataGeneratorService flightDataGeneratorService;
    private final FlightService flightService;

    public HttpRequestService(FlightDataGeneratorService flightDataGeneratorService, FlightService flightService) {
        this.flightDataGeneratorService = flightDataGeneratorService;
        this.flightService = flightService;
    }
    public List<Flight> getFlights(String url){
        List<Flight> flights = new ArrayList<>();
        		for (int i=0; i < 10; i++){
                    flights.add(flightDataGeneratorService.generateRandomFlight());
		}
         return flights;
    }
}
