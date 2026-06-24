package com.example.testspringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@DiscriminatorValue("ELECTRICBIKE")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ElectricBike extends Bike {


    @Positive
    private double autonomy;




}
