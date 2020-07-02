package org.taxi.paris.api.validators;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorMessageResponse {
    private final String message;
    private final LocalDateTime now;
    private final String violationConstraints;
    private final HttpStatus httpStatus;

    public ErrorMessageResponse(String message, LocalDateTime now, String violationConstraints, HttpStatus httpStatus) {
        this.message = message;
        this.now = now;
        this.violationConstraints = violationConstraints;
        this.httpStatus = httpStatus;
    }


}
