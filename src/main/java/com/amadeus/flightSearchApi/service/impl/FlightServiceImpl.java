package com.amadeus.flightSearchApi.service.impl;

import com.amadeus.flightSearchApi.dto.OneWayFlight;
import com.amadeus.flightSearchApi.dto.TwoWayFlight;
import com.amadeus.flightSearchApi.entity.Airport;
import com.amadeus.flightSearchApi.entity.Flight;
import com.amadeus.flightSearchApi.exception.NotFoundException;
import com.amadeus.flightSearchApi.repository.FlightRepository;
import com.amadeus.flightSearchApi.service.AirportService;
import com.amadeus.flightSearchApi.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);
    private final FlightRepository flightRepository;
    private final AirportService airportService;

    public FlightServiceImpl(FlightRepository flightRepository, AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
    }

    @Override
    public List<Flight> getAllFlights() {
        try {
            LOGGER.info("Retrieved {} flights from the database.", flightRepository.findAll());
            return flightRepository.findAll();

        } catch (Exception e) {
            LOGGER.error("Error getting all flights: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Flight getFlight(Long id) {
        Optional<Flight> byId = flightRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Flight"));
    }

    public List<Flight> searchFlight( Airport departure,  Airport arrival, String departureDate){

        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDate(departure,arrival,departureDate);
    }
    @Override
    public TwoWayFlight searchTwoWayFlightFlight(String departureAirport, String arrivalAirport, String departureDate, String returnDate){
        Airport departure = airportService.getByCity(departureAirport);
        Airport arrival = airportService.getByCity(arrivalAirport);
        List<Flight> departureFlights = searchFlight(departure,arrival,departureDate);
        List<Flight> returnFlights = searchFlight(arrival,departure,returnDate);

        return TwoWayFlight.getInstance(departureFlights,returnFlights);
    }
    @Override
    public OneWayFlight searchOneWayFlightFlight(String departureAirport, String arrivalAirport, String departureDate){
        Airport departure = airportService.getByCity(departureAirport);
        Airport arrival = airportService.getByCity(arrivalAirport);
        List<Flight> departureFlights = searchFlight(departure,arrival,departureDate);

        return OneWayFlight.getInstance(departureFlights);
    }
    @Override
    public Flight addFlight(Flight flight) {
        return  flightRepository.save(flight);
    }
    @Override
    public List<Flight> addFlights(List<Flight> flights) {
        return  flightRepository.saveAll(flights);
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
