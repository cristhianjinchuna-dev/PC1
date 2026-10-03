package org.example.pc1.Service;

import lombok.RequiredArgsConstructor;
import org.example.pc1.DTOs.PublicarViajeRequest;
import org.example.pc1.DTOs.PublicarViajeResponse;
import org.example.pc1.DTOs.ViajeDTO;
import org.example.pc1.Model.Trip;
import org.example.pc1.Model.User;
import org.example.pc1.Repository.TripRepository;
import org.example.pc1.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public PublicarViajeResponse publicar(PublicarViajeRequest request, User user) {
        Trip trip = new Trip();
        trip.setRoutedId(user.getId());
        trip.setDepartureTime(request.getDepartureTime());
        trip.setCapacity(request.getCapacity());
        trip.setStatus("SCHEDULED");

        Trip trip1 = tripRepository.save(trip);
        PublicarViajeResponse response = new PublicarViajeResponse();
        response.setAvailableSeats(trip1.getAvailableSeats());
        response.setDestination(request.getDestination());
        response.setId(trip1.getId());
        response.setOrigin(request.getOrigin());
        response.setRiverUsername(user.getUsername());
        response.setStatus("SCHEDULED");
        return response;
    }

    public Page<ViajeDTO> listar(Pageable pageable, origin, destination, from) {

        userRepository.findBy()

        List<ViajeDTO> result = tripRepository.findAll(pageable).stream()
                .filter(o -> destination == null || destination.equals((userRepository.findBy(o.getRoutedId())).getDestination))
                .filter(o -> origin == null || origin.equals((userRepository.findBy(o.getRoutedId())).getOrigin);
                                .filter(o -> from == null || from.equals(o.getDepartureTime))
                .sorted(comparator.comparing(Trip::getDepartureTime))
                .map(o-> {
                    ViajeDTO dto = new ViajeDTO();
                    dto.setAvaiableSeats(o.getCapacity);
                    dto.setDestination(userRepository.findBy(o.getRoutedId())).getDestination);
                })

    }
}
