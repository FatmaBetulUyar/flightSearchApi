package com.amadeus.flightSearchApi.service;

import com.amadeus.flightSearchApi.dto.OneWayFlight;
import com.amadeus.flightSearchApi.dto.TwoWayFlight;
import com.amadeus.flightSearchApi.entity.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlight(Long id);

    Flight addFlight(Flight flight);

    Flight updateFlight(Long id, Flight flight);

    void deleteFlight(Long id);

    public TwoWayFlight searchTwoWayFlightFlight(String departureAirport, String arrivalAirport, String departureDate, String returnDate);
    public OneWayFlight searchOneWayFlightFlight(String departureAirport, String arrivalAirport, String departureDate);
}
