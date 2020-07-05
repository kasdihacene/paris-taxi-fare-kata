package org.taxi.paris.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taxi.paris.api.domain.Price;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.exceptions.IncompleteDataBusinessException;
import org.taxi.paris.api.usecases.TaxiUseCase;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/ride")
@CrossOrigin(origins = "http://localhost:3000")
public class TaxiController {

    private TaxiUseCase taxiUseCase;

    public TaxiController(TaxiUseCase taxiUseCase) {
        this.taxiUseCase = taxiUseCase;
    }

    @PostMapping(value = "calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addPost(@RequestBody Ride ride) {
        try {
            Ride rideDTO = Ride.of(ride.getId(), ride.getDistance(), ride.getStartTime(), ride.getDuration());
            Price calculatedPrice = taxiUseCase.priceOf(rideDTO);
            return new ResponseEntity<Object>(calculatedPrice, HttpStatus.OK);

        } catch (ConstraintViolationException violationException) {
            throw new IncompleteDataBusinessException(violationException.getMessage());
        }
    }

}
