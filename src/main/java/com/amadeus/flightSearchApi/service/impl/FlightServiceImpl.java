package com.amadeus.flightSearchApi.service.impl;

import com.amadeus.flightSearchApi.entity.Flight;
import com.amadeus.flightSearchApi.repository.FlightRepository;
import com.amadeus.flightSearchApi.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);
    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public ResponseEntity<List<Flight>> getAllFlights() {
        try {
            LOGGER.info("Retrieved {} flights from the database.", flightRepository.findAll());
            return (ResponseEntity<List<Flight>>) flightRepository.findAll();

        } catch (Exception e) {
            LOGGER.error("Error getting all flights: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Flight getFlight(Long id) {
        Optional<Flight> byId = flightRepository.findById(id);
        return byId.orElseThrow(() -> new RuntimeException("Airport Not Found"));
    }

    @Override
    public Flight addFlight(Flight flight) {
        return  flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Long id, Flight flight) {
        getFlight(id);
        flight.setId(id);
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
         flightRepository.deleteById(id);
    }
}
