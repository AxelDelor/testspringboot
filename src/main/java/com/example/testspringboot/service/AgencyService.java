package com.example.testspringboot.service;

import com.example.testspringboot.exception.BikeNotAvailable;
import com.example.testspringboot.exception.LocationNotFound;
import com.example.testspringboot.model.Bike;
import com.example.testspringboot.model.Client;
import com.example.testspringboot.model.Location;
import com.example.testspringboot.model.State;
import com.example.testspringboot.repository.BikeRepository;
import com.example.testspringboot.repository.ClientRepository;
import com.example.testspringboot.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgencyService {
    private final BikeRepository bikeRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    public List<Bike> getBikes() {
        return bikeRepository.findAll();
    }

    public Optional<Bike> getBike(Long id) {
        return bikeRepository.findById(id);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Location addNewLocation(Long clientId, Long bikeId) {
        Client client = getClient(clientId).orElseThrow(() -> new RuntimeException("Aucun client n'a été trouvé"));
        Bike bike = getBike(bikeId).orElseThrow(() -> new RuntimeException("Aucun vélo n'a été trouvé"));
        checkAvailability(bike);
        bike.setState(State.RENTED);
        bikeRepository.save(bike);
        Location location = Location.builder()
                .bike(bike)
                .client(client)
                .startDate(LocalDateTime.now())
                .build();
        return locationRepository.save(location);
    }

    @Transactional
    public void endLocation(Long bikeId) {
        Location location = locationRepository.findByBikeIdAndEndDateIsNull(bikeId)
                .orElseThrow(() -> new LocationNotFound("La location n'a pas été trouvée"));
        location.setEndDate(LocalDateTime.now());
        locationRepository.save(location);
        Bike bike = location.getBike();
        bike.setState(State.AVAILABLE);
        bikeRepository.save(bike);
    }

    public Boolean checkAvailability(Bike bike) {
        if (bike.getState() == State.AVAILABLE) {
            return true;
        } else {
            throw new BikeNotAvailable("Le vélo n'est pas disponible actuellement");
        }
    }

    @Transactional
    public Client bestClientOfTheMonth() {
        return getClients().stream()
                .max(Comparator.comparingLong(client -> client.getHistory().stream()
                        .filter(location -> location.getStartDate().getMonth() == LocalDateTime.now().getMonth())
                        .count()))
                .orElseThrow(() -> new RuntimeException("Aucun client ne se démarque ce mois-ci"));
    }
}
