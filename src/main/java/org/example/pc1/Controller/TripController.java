package org.example.pc1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.pc1.DTOs.PagedResponseDto;
import org.example.pc1.DTOs.PublicarViajeRequest;
import org.example.pc1.DTOs.PublicarViajeResponse;
import org.example.pc1.DTOs.ViajeDTO;
import org.example.pc1.Model.User;
import org.example.pc1.Service.TripService;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;


    @PostMapping
    public ResponseEntity<PublicarViajeResponse> publicarViaje
            (@Valid @RequestBody PublicarViajeRequest request, @AuthenticationPrincipal User user) {

        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.publicar(request, user));
    }

    @GetMapping("/trips")
    public ResponseEntity<PagedResponseDto<ViajeDTO>> listar (@RequestParam (required = false) String origin,
                                                              @RequestParam (required = false) String destination,
                                                              @RequestParam (required = false)ZonedDateTime from,
                                                              @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(new PagedResponseDto<>(tripService.listar(origin, destination, from, pageable)));
    }
}
