package org.example.pc1.DTOs;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SignUpRequest {
    private String username;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{8,}$")
    private String password;
}
