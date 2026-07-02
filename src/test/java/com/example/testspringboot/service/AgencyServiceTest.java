package com.example.testspringboot.service;

import com.example.testspringboot.exception.BikeNotAvailable;
import com.example.testspringboot.exception.LocationNotFound;
import com.example.testspringboot.model.*;
import com.example.testspringboot.repository.BikeRepository;
import com.example.testspringboot.repository.ClientRepository;
import com.example.testspringboot.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    Location location01;


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
                .state(State.RENTED)
                .build();

        location01 = Location.builder()
                .client(client01)
                .bike(bike01)
                .build();
    }


    @Test
    public void testGetBikes() {
        when(bikeRepository.findAll())
                .thenReturn(List.of(bike01, bike02));

        List<Bike> result = agencyService.getBikes();
        assertEquals(2, result.size());
    }

    @Test
    public void checkAvailabilityTrue() {
        assertTrue(agencyService.checkAvailability(bike01));
    }

    @Test
    public void checkAvailabilityFalse() {
        assertThrows(BikeNotAvailable.class, () -> agencyService.checkAvailability(bike02));
    }

    @Test
    public void testAddNewLocation() {
        when(bikeRepository.findById(bike01.getId()))
                .thenReturn(Optional.of(bike01));

        when(clientRepository.findById(client01.getId()))
                .thenReturn(Optional.of(client01));

        when(bikeRepository.save(bike01))
                .thenReturn(bike01);

        when(locationRepository.save(any(Location.class)))
                .thenReturn(new Location());

        agencyService.addNewLocation(client01.getId(), bike01.getId());
        assertEquals(State.RENTED, bike01.getState());

    }

    @Test
    public void testEndLocation() {

        when(locationRepository.findByBikeIdAndEndDateIsNull(bike01.getId()))
                .thenReturn(Optional.ofNullable(location01));

        when(bikeRepository.save(bike01))
                .thenReturn(bike01);

        when(locationRepository.save(any(Location.class)))
                .thenReturn(new Location());

        agencyService.endLocation(bike01.getId());
        assertEquals(State.AVAILABLE, bike01.getState());
        assertNotNull(location01.getEndDate());

    }

}
