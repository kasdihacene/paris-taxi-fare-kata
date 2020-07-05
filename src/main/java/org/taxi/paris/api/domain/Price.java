package org.taxi.paris.api.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Price {
    private double price;

    private Price(double price) {
        this.price = price;
    }

    public static Price of(double price) {
        return new Price(price);
    }
}
