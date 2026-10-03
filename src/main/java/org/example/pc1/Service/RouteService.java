package org.example.pc1.Service;

import lombok.RequiredArgsConstructor;
import org.example.pc1.Model.Route;
import org.example.pc1.Repository.RouteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public Route save (Route route) {
        return routeRepository.save(route);
    }
}
