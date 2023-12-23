package com.amadeus.flightSearchApi.service;


import com.amadeus.flightSearchApi.dto.JwtAuthenticationResponse;
import com.amadeus.flightSearchApi.dto.SignUpRequest;
import com.amadeus.flightSearchApi.dto.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
