package com.melo.inventory.security;

import com.melo.inventory.model.AppUser;
import com.melo.inventory.model.AppUserResponse;
import com.melo.inventory.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(AppUserRepository appUserRepository, JwtService jwtService){
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    public AppUserResponse register(String email, String password){
        AppUser appUser = new AppUser();

        appUser.setEmail(email);
        appUser.setPassword(passwordEncoder.encode(password));

        AppUser savedUser = appUserRepository.save(appUser);

        return new AppUserResponse(savedUser.getId(), savedUser.getEmail());
    }

    public String login (String email, String password){
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));

        if (passwordEncoder.matches(password, appUser.getPassword())){
            return jwtService.generateToken(email);
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
    }
}
