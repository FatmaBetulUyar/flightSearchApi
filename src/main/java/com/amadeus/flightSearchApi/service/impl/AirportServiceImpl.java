package com.amadeus.flightSearchApi.service.impl;

import com.amadeus.flightSearchApi.entity.Airport;
import com.amadeus.flightSearchApi.repository.AirportRepository;
import com.amadeus.flightSearchApi.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public ResponseEntity<List<Airport>> getAllAirports() {
        return (ResponseEntity<List<Airport>>) airportRepository.findAll();
    }

    @Override
    public Optional<Airport> getAirport(Long id) {
        return airportRepository.findById(id);
    }

    @Override
    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Long id, Airport airport) {
        getAirport(id);
        airport.setId(id);
        return airportRepository.save(airport);
    }

    @Override
    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}
