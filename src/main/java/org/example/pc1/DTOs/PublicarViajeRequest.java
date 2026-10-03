package org.example.pc1.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PublicarViajeRequest {
    private String origin;
    private String destination;
    private ZonedDateTime departureTime;
    @Min(1) @Max(6)
    private Integer capacity;
}
