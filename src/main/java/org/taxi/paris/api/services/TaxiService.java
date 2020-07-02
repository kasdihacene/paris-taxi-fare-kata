package org.taxi.paris.api.services;

import org.springframework.stereotype.Service;
import org.taxi.paris.api.domain.Ride;
import org.taxi.paris.api.usecases.TaxiUseCase;

@Service
public class TaxiService implements TaxiUseCase {
    @Override
    public Double priceOf(Ride ride) {
        return 6.0;
    }
}
