package org.taxi.paris.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Ride data start time incorrect.")
public class RideDateTimeBusinessParseException extends RuntimeException {
    public RideDateTimeBusinessParseException(String message) {
        super(message);
    }
}
