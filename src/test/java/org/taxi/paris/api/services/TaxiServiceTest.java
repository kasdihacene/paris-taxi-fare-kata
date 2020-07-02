package org.taxi.paris.api.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;

public class TaxiServiceTest {

    @Test
    public void shouldCalculatePriceOfRideWhenHavingCorrectRideObject() {
        // ARRANGE
        Double expectedPrice = 6.0;
        Ride ride = new Ride(1, 2, "2020-06-19T13:01:17.031Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Double actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldCalculatePriceWhenRiding3MilesInOutsideBusyPeriod(){
        // ARRANGE
        Double expectedPrice = 8.5;
        Ride ride = new Ride(1, 3, "2020-06-19T13:01:17.031Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Double actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }
}
