package com.example.testspringboot.service;

import com.example.testspringboot.model.Bike;
import com.example.testspringboot.model.Client;
import com.example.testspringboot.model.StandardBike;
import com.example.testspringboot.model.State;
import com.example.testspringboot.repository.BikeRepository;
import com.example.testspringboot.repository.ClientRepository;
import com.example.testspringboot.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgencyServiceTest {

    @Mock
    private BikeRepository bikeRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private AgencyService agencyService;

    Client client01;
    Bike bike01;
    Bike bike02;


    @BeforeEach
    void initializingData() {

        client01 = Client.builder()
                .name("Dude")
                .email("Dude@test.com")
                .build();

        bike01 = StandardBike.builder()
                .state(State.AVAILABLE)
                .build();

        bike02 = StandardBike.builder()
                .state(State.AVAILABLE)
                .build();
    }


    @Test
    public void testGetBikes() {
        when(bikeRepository.findAll())
                .thenReturn(List.of(bike01, bike02));

        List<Bike> result = agencyService.getBikes();
        assertEquals(2, result.size());


    }

}
