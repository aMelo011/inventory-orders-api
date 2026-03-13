package com.melo.inventory.controller;

import com.melo.inventory.model.AppUser;
import com.melo.inventory.model.AppUserResponse;
import com.melo.inventory.model.AuthRequest;
import com.melo.inventory.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){this.authService = authService;}

    @PostMapping("auth/register")
    public ResponseEntity<AppUserResponse> registerAppUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(authRequest.getEmail(), authRequest.getPassword()));
    }

    @PostMapping("auth/login")
    public ResponseEntity<String> loginAppUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest.getEmail(), authRequest.getPassword()));
    }
}
