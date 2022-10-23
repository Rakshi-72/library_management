package org.training.library_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.security.jwt.JWTAuthResponse;
import org.training.library_management.security.jwt.JWTHelper;
import org.training.library_management.security.jwt.JWTRequest;

@RestController
@RequestMapping("/api/jwt")
public class JWTController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsService service;

    @Autowired
    private JWTHelper utils;

    /**
     * The function takes a username and password, authenticates the user, and
     * returns a token
     * 
     * @param request This is the request body that is sent to the server.
     * @return A JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> getToken(@RequestBody JWTRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        } catch (Exception e) {
            throw new UsernameNotFoundException("username or password is wrong");
        }

        UserDetails userDetails = service.loadUserByUsername(request.getUserName());
        String token = utils.generateToken(userDetails);
        return new ResponseEntity<>(new JWTAuthResponse(token), HttpStatus.ACCEPTED);
    }
}
