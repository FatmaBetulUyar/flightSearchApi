package com.amadeus.flightSearchApi.service.impl;

import com.amadeus.flightSearchApi.entity.Flight;
import com.amadeus.flightSearchApi.service.FlightService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FlightInfoJob implements Job {

    private final RestTemplate restTemplate;
    private final FlightDataGeneratorService flightDataGeneratorService;
    private final FlightService flightService;

    private final HttpRequestService httpRequestService;

    public FlightInfoJob(RestTemplate restTemplate, FlightDataGeneratorService flightDataGeneratorService, FlightService flightService, HttpRequestService httpRequestService) {
        this.restTemplate = restTemplate;
        this.flightDataGeneratorService = flightDataGeneratorService;
        this.flightService = flightService;
        this.httpRequestService = httpRequestService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        String apiUrl = "http://localhost:8080/api/flight-info";
//        Flight mockFlight = restTemplate.getForObject(apiUrl, Flight.class);

        List <Flight> flights =  httpRequestService.getFlights(apiUrl);

        flightService.addFlights(flights);
    }

}
