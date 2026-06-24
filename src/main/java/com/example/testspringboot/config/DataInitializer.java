package com.example.testspringboot.config;

import com.example.testspringboot.model.Client;
import com.example.testspringboot.model.ElectricBike;
import com.example.testspringboot.model.StandardBike;
import com.example.testspringboot.model.State;
import com.example.testspringboot.repository.BikeRepository;
import com.example.testspringboot.repository.ClientRepository;
import com.example.testspringboot.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BikeRepository bikeRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    @Override
    public void run(String... args) throws Exception {
        StandardBike bike1 = StandardBike.builder().state(State.AVAILABLE).build();
        StandardBike bike2 = StandardBike.builder().state(State.AVAILABLE).build();
        StandardBike bike3 = StandardBike.builder().state(State.AVAILABLE).build();
        StandardBike bike4 = StandardBike.builder().state(State.GARAGE).build();
        StandardBike bike5 = StandardBike.builder().state(State.AVAILABLE).build();
        ElectricBike bike6 = ElectricBike.builder().state(State.AVAILABLE).autonomy(80.0).build();
        ElectricBike bike7 = ElectricBike.builder().state(State.AVAILABLE).autonomy(60.0).build();
        ElectricBike bike8 = ElectricBike.builder().state(State.RENTED).autonomy(45.0).build();
        ElectricBike bike9 = ElectricBike.builder().state(State.AVAILABLE).autonomy(90.0).build();
        ElectricBike bike10 = ElectricBike.builder().state(State.AVAILABLE).autonomy(30.0).build();

        bikeRepository.saveAll(List.of(bike1, bike2, bike3, bike4, bike5, bike6, bike7, bike8, bike9, bike10));

        Client client1 = Client.builder().name("Alice Dupont").email("alice@test.com").build();
        Client client2 = Client.builder().name("Bob Martin").email("bob@test.com").build();
        Client client3 = Client.builder().name("Clara Bernard").email("clara@test.com").build();
        Client client4 = Client.builder().name("David Petit").email("david@test.com").build();
        Client client5 = Client.builder().name("Emma Leroy").email("emma@test.com").build();
        Client client6 = Client.builder().name("François Durand").email("francois@test.com").build();
        Client client7 = Client.builder().name("Grace Morel").email("grace@test.com").build();
        Client client8 = Client.builder().name("Hugo Simon").email("hugo@test.com").build();
        Client client9 = Client.builder().name("Inès Lambert").email("ines@test.com").build();
        Client client10 = Client.builder().name("Jules Rousseau").email("jules@test.com").build();

        clientRepository.saveAll(List.of(client1, client2, client3, client4, client5, client6, client7, client8, client9, client10));

    }
}
