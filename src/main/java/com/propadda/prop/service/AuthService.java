package com.propadda.prop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.propadda.prop.dto.AuthResponse;
import com.propadda.prop.dto.LoginRequest;
import com.propadda.prop.dto.RegisterRequest;
import com.propadda.prop.model.Users;
import com.propadda.prop.repo.UsersRepo;
import com.propadda.prop.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        Users user = new Users();
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(request.role);
        user.setFirstName(request.firstName);
        user.setLastName(request.lastName);
        user.setPhoneNumber(request.phoneNumber);
        user.setState(request.state);
        user.setCity(request.city);
        usersRepo.save(user);
        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt, user.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        Users user = usersRepo.findByEmail(request.email);

        if (user!=null && !passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt, user.getRole().name());
    }
    
}
