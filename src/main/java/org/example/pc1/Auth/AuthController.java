package org.example.pc1.Auth;

import lombok.RequiredArgsConstructor;
import org.example.pc1.DTOs.SignInRequest;
import org.example.pc1.DTOs.SignInResponse;
import org.example.pc1.DTOs.SignUpRequest;
import org.example.pc1.DTOs.SignUpResponse;
import org.example.pc1.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<SignUpResponse> registerUser(@RequestBody SignUpRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> loginUser(@RequestBody SignInRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(request));
    }
}
