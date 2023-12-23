package com.amadeus.flightSearchApi.dto;

import com.amadeus.flightSearchApi.entity.Flight;

import java.util.List;

public class TwoWayFlight {
    private List<Flight> departureFlights;
    private List<Flight> returnFlights;

    public static TwoWayFlight getInstance(List<Flight> departureFlights,List<Flight> returnFlights){
        TwoWayFlight twoWayFlight = new TwoWayFlight();
        twoWayFlight.setDepartureFlights(departureFlights);
        twoWayFlight.setReturnFlights(returnFlights);
        return twoWayFlight;
    }

    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public void setDepartureFlights(List<Flight> departureFlights) {
        this.departureFlights = departureFlights;
    }

    public List<Flight> getReturnFlights() {
        return returnFlights;
    }

    public void setReturnFlights(List<Flight> returnFlights) {
        this.returnFlights = returnFlights;
    }
}
