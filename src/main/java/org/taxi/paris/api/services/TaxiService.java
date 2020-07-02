package org.taxi.paris.api.services;

import org.springframework.stereotype.Service;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;

import java.time.LocalDateTime;

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
        LocalDateTime dateTime = ride.localDateTime();

        double fifthOfMilePrice = (ride.getDistance() * 5) * 0.5;

        int initialCharge = 1;
        if (new Ride().isBetween6PMand7PM(dateTime)) {
            Double price = initialCharge + 1 + fifthOfMilePrice;
            return price;
        }

        if (new Ride().isBetween8PMand6AM(dateTime)) {
            Double price = initialCharge + 0.5 + fifthOfMilePrice;
            return price;
        }

        Double price = initialCharge + fifthOfMilePrice;
        return price;
    }

}
