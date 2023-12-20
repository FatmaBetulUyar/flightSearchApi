package com.amadeus.flightSearchApi.repository;

import com.amadeus.flightSearchApi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
}
