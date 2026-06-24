package com.example.testspringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@DiscriminatorValue("STANDARDBIKE")
@SuperBuilder
@AllArgsConstructor
public class StandardBike extends Bike {

}
