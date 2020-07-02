package org.taxi.paris.api.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.taxi.paris.api.domain.Price;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;

public class TaxiServiceTest {

    @Test
    public void shouldCalculatePriceOfRideWhenHavingCorrectRideObject() {
        // ARRANGE
        Price expectedPrice = Price.of(6.0);
        Ride ride = new Ride(1, 2, "2020-06-19T13:01:17.031Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldCalculatePriceWhenRiding3MilesInOutsideBusyPeriod() {
        // ARRANGE
        Price expectedPrice = Price.of(8.5);
        Ride ride = new Ride(1, 3, "2020-06-19T13:01:17.031Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnPriceRelatedToBusyPeriodWhenRidingAt4PM() {
        // ARRANGE
        Price expectedPrice = Price.of(12.0);
        Ride ride = new Ride(1, 4, "2020-06-19T16:01:17.031Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnPriceRelatedToBusyPeriodWhenRidingAt7PM() {
        // ARRANGE
        Price expectedPrice = Price.of(12.0);
        Ride ride = new Ride(1, 4, "2020-06-19T19:01:17.031Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnPriceRelatedToBusyPeriodWhenRidingAt4AM() {
        // ARRANGE
        Price expectedPrice = Price.of(12.0);
        Ride ride = new Ride(1, 4, "2020-06-19T16:00:00.000Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnPriceRelatedToBusyPeriodWhenRidingAt8PM() {
        // ARRANGE
        Price expectedPrice = Price.of(11.5);
        Ride ride = new Ride(1, 4, "2020-06-19T20:00:00.000Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnPriceRelatedToBusyPeriodWhenRidingAfter8PMAndBefore6AM() {
        // ARRANGE
        Price expectedPrice = Price.of(11.5);
        Ride ride = new Ride(1, 4, "2020-06-19T01:00:00.000Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnPriceRelatedToBusyPeriodWhenRidingAtMidnight() {
        // ARRANGE
        Price expectedPrice = Price.of(11.5);
        Ride ride = new Ride(1, 4, "2020-06-19T00:00:00.000Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnPriceRelatedToBusyPeriodWhenRidingAt6AM() {
        // ARRANGE
        Price expectedPrice = Price.of(11.5);
        Ride ride = new Ride(1, 4, "2020-06-19T06:20:00.000Z", 9000);
        TaxiUseCase taxiUseCase = new TaxiService();

        // ACT
        Price actualPrice = taxiUseCase.priceOf(ride);

        // ASSERT
        Assertions.assertThat(actualPrice).isEqualTo(expectedPrice);
    }


}
