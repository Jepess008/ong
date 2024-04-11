package com.campuslands.ong.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.ong.repositories.models.JWTRequest;
import com.campuslands.ong.repositories.models.JWTResponse;
import com.campuslands.ong.services.JWTUserDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.campuslands.ong.services.JWTService;



@RestController
@AllArgsConstructor
@Tag(name = "Authentication", description = "Genera un codigo de autenticacion para el usuario")
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTUserDetailService jwtUserDetailService;
    private final JWTService jwtService;

    @Operation(summary = "Obtener un token a través de nombre de usuario y contraseña")
    @PostMapping("/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request) {
        log.error("Haciendo uso del controlador de autenticacion");
        this.authenticate(request);

        final var userDetails = this.jwtUserDetailService.loadUserByUsername(request.getUsername());

        final var token = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(JWTRequest request) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            log.info("Login realizado con exito");
            
        } catch (BadCredentialsException | DisabledException e) {
            log.warn("Algo salio mal");
            throw new RuntimeException(e.getMessage());
        }
    }
}
