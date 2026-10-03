package org.example.pc1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ViajeDTO {
    private Long id;
    private String driverUsername;
    private String destination;
    private Integer avaiableSeats;
}
