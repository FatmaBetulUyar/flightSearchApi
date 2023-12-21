package com.amadeus.flightSearchApi.service;

import com.amadeus.flightSearchApi.entity.Airport;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    List<Airport> getAllAirports();

    Optional<Airport> getAirport(Long id);

    Airport addAirport(Airport airport);

    Airport updateAirport(Long id, Airport airport);

    Airport getByCity(String city);

    void deleteAirport(Long id);
}