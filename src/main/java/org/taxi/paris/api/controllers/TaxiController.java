package org.taxi.paris.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;
import org.taxi.paris.api.validators.ErrorMessageResponse;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
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
            Ride rideDTO = new Ride(ride.getId(), ride.getDistance(), ride.getStartTime(), ride.getDuration());
            Double calculatedPrice = taxiUseCase.priceOf(rideDTO);
            return new ResponseEntity<>(calculatedPrice, HttpStatus.OK);

        } catch (ConstraintViolationException violationException) {
            HttpStatus preconditionRequired = HttpStatus.PRECONDITION_REQUIRED;
            ErrorMessageResponse errorMessage = new ErrorMessageResponse(violationException.getMessage(), LocalDateTime.now(), "Please complete all required data.", preconditionRequired);
            return new ResponseEntity<>(errorMessage, preconditionRequired);
        }
    }

    @GetMapping(value = "populate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> populateRideCompany() {
        List rides = Arrays.asList(
                new Ride(1, 2, "2020-06-19T13:01:17.031Z", 9000),
                new Ride(2, 1, "2020-06-19T12:01:17.031Z", 6000),
                new Ride(3, 5, "2020-06-19T14:01:17.031Z", 7000),
                new Ride(4, 5, "2020-06-19T14:11:17.031Z", 4000)
        );
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

}
