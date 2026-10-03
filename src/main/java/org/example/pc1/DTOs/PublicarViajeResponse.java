package org.example.pc1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PublicarViajeResponse {
    private Long id;
    private String riverUsername;
    private String origin;
    private String destination;
    private Integer availableSeats;
    private String status;
}
