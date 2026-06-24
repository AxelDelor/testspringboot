package com.example.testspringboot.controller;

import com.example.testspringboot.model.Client;
import com.example.testspringboot.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/clients")
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final AgencyService agencyService;

    @GetMapping("/best")
    public Client getBestEmployeeOfTheMonth() {
        return agencyService.bestClientOfTheMonth();
    }

}
