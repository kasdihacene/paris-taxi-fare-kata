package org.taxi.paris.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taxi.paris.api.domain.ErrorMessageResponse;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

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

}
