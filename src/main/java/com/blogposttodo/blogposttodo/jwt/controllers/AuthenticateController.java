package com.blogposttodo.blogposttodo.jwt.controllers;

import com.blogposttodo.blogposttodo.jwt.models.AuthenticationRequest;
import com.blogposttodo.blogposttodo.jwt.models.AuthenticationResponse;
import com.blogposttodo.blogposttodo.jwt.services.ApplicationUserDetailsService;
import com.blogposttodo.blogposttodo.jwt.util.JwtUtil;
import com.blogposttodo.blogposttodo.userlogin.entity.UserLoginEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        UserLoginEntity user;

        try {
            user = userDetailsService.authenticate(request.getEmail(), request.getPassword());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        var userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        System.out.println("user details" + userDetails);
        var jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);

    }

}