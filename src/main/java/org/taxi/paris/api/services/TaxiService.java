package org.taxi.paris.api.services;

import org.springframework.stereotype.Service;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;

@Service
public class TaxiService implements TaxiUseCase {

    public static final int ADDITIONAL_FOR_BUSY_PERIOD = 1;
    public static final double ADDITIONAL_FOR_PERIOD_BETWEEN_8PM_6AM = 0.5;

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

        double fifthOfMilePrice = ride.fifthOfMileCompute();
        int initialCharge = 1;

        if (ride.isBetween6PMand7PM()) {
            return initialCharge + ADDITIONAL_FOR_BUSY_PERIOD + fifthOfMilePrice;
        }

        if (ride.isBetween8PMand6AM()) {
            return initialCharge + ADDITIONAL_FOR_PERIOD_BETWEEN_8PM_6AM + fifthOfMilePrice;
        }

        return initialCharge + fifthOfMilePrice;
    }

}
