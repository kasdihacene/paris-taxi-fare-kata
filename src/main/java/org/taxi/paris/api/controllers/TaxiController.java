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
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/ride")
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

    @GetMapping(value = "populate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> populateRideCompany() {
        List rides = Arrays.asList(
                Ride.of(1, 2, "2020-06-19T13:01:17.031Z", 9000),
                Ride.of(2, 1, "2020-06-19T12:01:17.031Z", 6000),
                Ride.of(3, 5, "2020-06-19T14:01:17.031Z", 7000),
                Ride.of(4, 5, "2020-06-19T14:11:17.031Z", 4000)
        );
        return new ResponseEntity<Object>(rides, HttpStatus.OK);
    }

}
