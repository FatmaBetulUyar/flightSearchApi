package com.amadeus.flightSearchApi.controller;

import com.amadeus.flightSearchApi.dto.OneWayFlight;
import com.amadeus.flightSearchApi.dto.TwoWayFlight;
import com.amadeus.flightSearchApi.entity.Flight;
import com.amadeus.flightSearchApi.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Flight>>getAllFlights() {
        return new ResponseEntity<>(flightService.getAllFlights(),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAirport(@PathVariable Long id) {
        Flight flight = flightService.getFlight(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }
    @GetMapping("/flights/twoWay")
    public TwoWayFlight getFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam String departureDate,
            @RequestParam String returnDate
    ) {
        return flightService.searchTwoWayFlightFlight(departureCity, arrivalCity, departureDate, returnDate);
    }
    @GetMapping("/flights/oneWay")
    public OneWayFlight getFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam String departureDate

    ) {
            return flightService.searchOneWayFlightFlight(departureCity, arrivalCity, departureDate);
    }

    @PostMapping(value = "/create")
    public Flight saveFlight(@RequestBody Flight flight) {
       return flightService.addFlight(flight);
    }

    @PutMapping(value = "/update/{id}")
    public Flight updateAirport(@PathVariable Long id, @RequestBody Flight flight) {
        return flightService.updateFlight(id,flight);
    }

    @DeleteMapping(value = "/delete")
    public void deleteFlight(@RequestParam Long id) {
         flightService.deleteFlight(id);
    }
}
