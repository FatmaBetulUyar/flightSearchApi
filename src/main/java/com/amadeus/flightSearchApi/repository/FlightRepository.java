package com.amadeus.flightSearchApi.repository;

import com.amadeus.flightSearchApi.entity.Airport;
import com.amadeus.flightSearchApi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f " +
            "WHERE f.departureAirport = :departureCity " +
            "AND f.arrivalAirport = :arrivalCity " +
            "AND f.departureDate = :departureDateTime "  )
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDate(
                    @Param("departureCity") Airport departureCity,
                    @Param("arrivalCity") Airport arrivalCity,
                    @Param("departureDateTime") String departureDateTime);

}
