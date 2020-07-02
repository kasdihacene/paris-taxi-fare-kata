package org.taxi.paris.api.usecases;

import org.taxi.paris.api.domain.Price;
import org.taxi.paris.api.domain.Ride;

public interface TaxiUseCase {
    Price priceOf(Ride ride);
}
