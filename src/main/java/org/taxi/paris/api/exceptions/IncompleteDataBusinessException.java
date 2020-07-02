package org.taxi.paris.api.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED, reason = "Ride data required.")
public class IncompleteDataBusinessException extends RuntimeException {
    public IncompleteDataBusinessException(String message) {
        super(message);
    }
}
