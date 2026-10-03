package org.example.pc1.Auth;

import lombok.RequiredArgsConstructor;
import org.example.pc1.DTOs.SignInRequest;
import org.example.pc1.DTOs.SignInResponse;
import org.example.pc1.DTOs.SignUpRequest;
import org.example.pc1.DTOs.SignUpResponse;
import org.example.pc1.Exceptions.InvalidCredentialException;
import org.example.pc1.Exceptions.UserAlreadyExistsException;
import org.example.pc1.Model.Role;
import org.example.pc1.Model.User;
import org.example.pc1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Value("${jwt.expiration.access}")
    private Long expirationMs;

    public SignUpResponse signUp(SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Ya existe un usuario con esas credenciales");
        }

        User user = userRepository.save(new User (request.getUsername(),
                request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.ROLE_PASSENGER));

        SignUpResponse response = new SignUpResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }

    public SignInResponse signIn(SignInRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new InvalidCredentialException("Usuario o contraseña incorrecta");
        }

        var user = userRepository.findByUsername(request.getUsername()).orElse(null);

        SignInResponse response = new SignInResponse();
        response.setToken(jwtService.generateToken(user));
        response.setExpiresIn(expirationMs/1000);
        return response;
    }
}
