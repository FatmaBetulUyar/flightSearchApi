package com.amadeus.flightSearchApi.controller;

import com.amadeus.flightSearchApi.entity.Airport;
import com.amadeus.flightSearchApi.entity.Flight;
import com.amadeus.flightSearchApi.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {
    private final AirportService airportService;
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Airport>> getAllAirports() {
        return new ResponseEntity<>(airportService.getAllAirports(), HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAirport(@PathVariable Long id) {
        Optional<Airport> airport = airportService.getAirport(id);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public Airport saveAirport(@RequestBody Airport airport) {
        return airportService.addAirport(airport);
    }

    @PutMapping(value = "/update/{id}")
    public Airport updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        return airportService.updateAirport(id,airport);
    }

    @DeleteMapping(value = "/delete")
    public void deleteAirport(@RequestParam Long id) {
        airportService.deleteAirport(id);
    }
}
