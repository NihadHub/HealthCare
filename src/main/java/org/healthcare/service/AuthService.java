package org.healthcare.service;

import lombok.RequiredArgsConstructor;
import org.healthcare.dto.AuthResponse;
import org.healthcare.dto.LoginRequest;
import org.healthcare.dto.RegisterRequest;
import org.healthcare.entity.User;
import org.healthcare.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthResponse register(RegisterRequest req){
        if(userRepository.existsByUsername(req.getUsername())){
            throw new IllegalArgumentException("Username déja utilisé");
        }
        if (userRepository.existsByEmail(req.getEmail())){
            throw new  IllegalArgumentException("Email deja utilisé");
        }
        User user= new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest req){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword())
        );
        User user = userRepository.findByUsername(req.getUsername()).orElseThrow(()-> new UsernameNotFoundException("utilisateur introuvable"));
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }




















}
