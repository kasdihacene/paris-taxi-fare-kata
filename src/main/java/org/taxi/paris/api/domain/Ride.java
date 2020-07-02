package org.taxi.paris.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.taxi.paris.api.exceptions.RideDateTimeBusinessParseException;
import org.taxi.paris.api.validators.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.*;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
public class Ride extends SelfValidating {

    private static final int ADDITIONAL_FOR_BUSY_PERIOD = 1;
    private static final double ADDITIONAL_FOR_PERIOD_BETWEEN_8PM_6AM = 0.5;

    private int id;
    @Positive
    private int distance;
    @NotBlank
    private String startTime;
    @Positive
    private int duration;

    private Ride(int id, int distance, String startTime, int duration) {
        this.id = id;
        this.distance = distance;
        this.startTime = startTime;
        this.duration = duration;
        super.validateSelf();
    }

    public static Ride of(int id, int distance, String startTime, int duration) {
        return new Ride(id, distance, startTime, duration);
    }

    private LocalDateTime localDateTime() {
        try {
            return LocalDateTime.ofInstant(Instant.parse(this.startTime), ZoneId.of(ZoneOffset.UTC.getId()));
        } catch (DateTimeParseException parseException) {
            throw new RideDateTimeBusinessParseException(parseException.getMessage());
        }
    }

    private boolean isBetween8PMand6AM() {
        LocalDateTime dateTime = localDateTime();
        return (
                (dateTime.toLocalTime().isAfter(LocalTime.of(20, 0)) || dateTime.toLocalTime().equals(LocalTime.of(20, 0)))
                        && dateTime.toLocalTime().isBefore(LocalTime.MAX)) ||
                ((dateTime.toLocalTime().isAfter(LocalTime.MIDNIGHT) || (dateTime.toLocalTime().equals(LocalTime.MIDNIGHT)))
                        && dateTime.toLocalTime().isBefore(LocalTime.of(7, 0)));
    }

    private boolean isBetween6PMand7PM() {
        LocalDateTime dateTime = localDateTime();
        return (dateTime.toLocalTime().isAfter(LocalTime.of(16, 0)) || dateTime.toLocalTime().equals(LocalTime.of(16, 0)))
                && dateTime.toLocalTime().isBefore(LocalTime.of(20, 0));
    }

    private double fifthOfMileCompute() {
        return (this.distance * 5) * 0.5;
    }

    public Price computePrice() {
        double fifthOfMilePrice = fifthOfMileCompute();
        int initialCharge = 1;

        if (isBetween6PMand7PM()) {
            double price = initialCharge + ADDITIONAL_FOR_BUSY_PERIOD + fifthOfMilePrice;

            return Price.of(price);
        }

        if (isBetween8PMand6AM()) {
            double price = initialCharge + ADDITIONAL_FOR_PERIOD_BETWEEN_8PM_6AM + fifthOfMilePrice;
            return Price.of(price);
        }

        double price = initialCharge + fifthOfMilePrice;
        return Price.of(price);
    }
}
