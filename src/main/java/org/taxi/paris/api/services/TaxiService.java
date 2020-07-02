package org.taxi.paris.api.services;

import org.springframework.stereotype.Service;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;

import java.time.*;

@Service
public class TaxiService implements TaxiUseCase {
    /**
     * ***************************
     * ********** RULES **********
     * ***************************
     * Initial charge : 1 EUR
     * 1/5 th of a mile : 0.5 EUR
     * Additional 0.5 EUR when start time between 8PM (20:00) and 6AM (6:00)
     * 1 EUR additional for busy periods between 4PM (16:00) and 7PM (19:00)
     * ***************************
     */

    @Override
    public Double priceOf(Ride ride) {
        String startTime = ride.getStartTime();
        Instant instant = Instant.parse(startTime);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

        if ((dateTime.toLocalTime().isAfter(LocalTime.of(16, 0)) || dateTime.toLocalTime().equals(LocalTime.of(16, 0)))
                && dateTime.toLocalTime().isBefore(LocalTime.of(20, 0))) {
            Double price = 1 + 1 + (ride.getDistance() * 5) * 0.5;
            return price;
        }

        Double price = 1 + (ride.getDistance() * 5) * 0.5;
        return price;
    }
}
