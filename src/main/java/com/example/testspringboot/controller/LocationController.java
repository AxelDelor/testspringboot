package com.example.testspringboot.controller;

import com.example.testspringboot.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {

    private final AgencyService agencyService;

    @PostMapping
    public void createLocation(@RequestParam Long clientId, @RequestParam Long bikeId) {
        agencyService.addNewLocation(clientId, bikeId);
    }

    @DeleteMapping("/{bikeId}")
    public void deleteLocation(@PathVariable Long bikeId) {
        agencyService.endLocation(bikeId);
    }


}
