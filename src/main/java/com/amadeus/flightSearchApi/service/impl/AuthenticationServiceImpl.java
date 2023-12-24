package com.amadeus.flightSearchApi.service.impl;

import com.amadeus.flightSearchApi.dto.JwtAuthenticationResponse;
import com.amadeus.flightSearchApi.dto.SignUpRequest;
import com.amadeus.flightSearchApi.dto.SigninRequest;
import com.amadeus.flightSearchApi.entity.Role;
import com.amadeus.flightSearchApi.entity.User;
import com.amadeus.flightSearchApi.exception.CustomJwtException;
import com.amadeus.flightSearchApi.repository.UserRepository;
import com.amadeus.flightSearchApi.service.AuthenticationService;
import com.amadeus.flightSearchApi.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())){
            var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                    .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER).build();
            userRepository.save(user);
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        }else {
            throw new CustomJwtException("Email is already in use", HttpStatus.BAD_REQUEST);
        }

    }
    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new  CustomJwtException("Invalid username/password supplied", HttpStatus.BAD_REQUEST));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
