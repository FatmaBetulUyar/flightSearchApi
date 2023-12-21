package com.amadeus.flightSearchApi.service.impl;

import com.amadeus.flightSearchApi.entity.Airport;
import com.amadeus.flightSearchApi.entity.Flight;
import com.amadeus.flightSearchApi.service.AirportService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class FlightDataGeneratorService {
    private List<Airport> airports;
    private final AirportService airportService;

    public FlightDataGeneratorService(List<Airport> airports, AirportService airportService) {
        this.airports = airports;
        this.airportService = airportService;
    }

    public Flight generateRandomFlight() {
        Flight flight = new Flight();
        airports = airportService.getAllAirports();

        if (airports.isEmpty()) {
            throw new IllegalStateException("Airport list is empty");
        }

        Airport departureAirport = getRandomAirport();
        Airport arrivalAirport = getRandomAirport();

        while (departureAirport.getId().equals(arrivalAirport.getId())) {
            arrivalAirport = getRandomAirport();
        }

        LocalDateTime departureDateTime = generateRandomLocalDateTime();
        LocalDateTime returnDateTime = generateRandomLocalDateTimeAfter(departureDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String departureDateTimeString = departureDateTime.format(formatter);
        String returnDateTimeString = returnDateTime.format(formatter);

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        String departurTimeString = departureDateTime.format(formatterTime);
        String returnTimeString = returnDateTime.format(formatterTime);

        double price = generateRandomPrice();

        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureTime(departurTimeString);
        flight.setReturnTime(returnTimeString);
        flight.setDepartureDate(departureDateTimeString);
        flight.setReturnDate(returnDateTimeString);
        flight.setPrice(price);

        return flight;
    }

    private Airport getRandomAirport() {
        Random random = new Random();
        return airports.get(Math.abs(random.nextInt(airports.size())));
    }
    public static LocalDateTime generateRandomLocalDateTime() {
        long currentEpochMillis = System.currentTimeMillis();
        long randomMillisSinceEpoch = currentEpochMillis + TimeUnit.DAYS.toMillis(Math.abs(ThreadLocalRandom.current().nextInt(365)));
        Instant instant = Instant.ofEpochMilli(randomMillisSinceEpoch);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime generateRandomLocalDateTimeAfter(LocalDateTime dateTime) {
        long currentEpochMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long randomMillisSinceEpoch = currentEpochMillis + TimeUnit.DAYS.toMillis(Math.abs(ThreadLocalRandom.current().nextInt(10)));
        Instant instant = Instant.ofEpochMilli(randomMillisSinceEpoch);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private double generateRandomPrice() {
        return Math.round((100 + new Random().nextDouble() * 900) * 100.0) / 100.0;
    }
}
