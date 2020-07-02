package org.taxi.paris.api.usecases;

import org.taxi.paris.api.domain.Ride;

public interface TaxiUseCase {
    Double priceOf(Ride ride);
}
