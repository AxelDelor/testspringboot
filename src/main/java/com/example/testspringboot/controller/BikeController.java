package com.example.testspringboot.controller;

import com.example.testspringboot.model.Bike;
import com.example.testspringboot.model.Client;
import com.example.testspringboot.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bikes")
public class BikeController {

    private final AgencyService agencyService;

    @GetMapping
    public List<Bike> getBikes() {
        return agencyService.getBikes();
    }

//    @PostMapping("/location")
//    public void createLocation(@PathVariable long clientId, @PathVariable long bikeId) {
//        agencyService.addNewLocation(clientId, bikeId);
//    }
//
//
////
////    @PostMapping("/location/id")
////    public void endLocation(@PathVariable long bikeId) {
////        agencyService.endLocation(bikeId);
////    }
////
////    @GetMapping("/client")
////    public Client getBestEmployeeOfTheMonth() {
////        return agencyService.getBestClientOfTheMonth();
////    }


}
