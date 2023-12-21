package com.amadeus.flightSearchApi.dto;

import com.amadeus.flightSearchApi.entity.Airport;
import com.amadeus.flightSearchApi.entity.Flight;

import java.util.List;

public class OneWayFlight {
    private List<Flight> departureFlights;

    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }
    public static OneWayFlight getInstance(List<Flight> departureFlights){
        OneWayFlight oneWayFlight = new OneWayFlight();
        oneWayFlight.setDepartureFlights(departureFlights);
        return oneWayFlight;
    }
    public void setDepartureFlights(List<Flight> departureFlights) {
        this.departureFlights = departureFlights;
    }
}
