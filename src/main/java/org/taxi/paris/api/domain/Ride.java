package org.taxi.paris.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.*;

@Getter
@Setter
public class Ride {

    private int id;
    private int distance;
    private String startTime;
    private int duration;

    public Ride() {
    }

    public Ride(int id, int distance, String startTime, int duration) {
        this.id = id;
        this.distance = distance;
        this.startTime = startTime;
        this.duration = duration;
    }

    public boolean isBetween6PMand7PM(LocalDateTime dateTime) {
        return (dateTime.toLocalTime().isAfter(LocalTime.of(16, 0)) || dateTime.toLocalTime().equals(LocalTime.of(16, 0)))
                && dateTime.toLocalTime().isBefore(LocalTime.of(20, 0));
    }

    public boolean isBetween8PMand6AM(LocalDateTime dateTime) {
        return ((dateTime.toLocalTime().isAfter(LocalTime.of(20, 0)) || dateTime.toLocalTime().equals(LocalTime.of(20, 0)))
                && dateTime.toLocalTime().isBefore(LocalTime.MAX)) ||
                ((dateTime.toLocalTime().isAfter(LocalTime.MIDNIGHT) || (dateTime.toLocalTime().equals(LocalTime.MIDNIGHT)))
                        && dateTime.toLocalTime().isBefore(LocalTime.of(7, 0)));
    }

    public LocalDateTime localDateTime() {
        return LocalDateTime.ofInstant(Instant.parse(this.startTime), ZoneId.of(ZoneOffset.UTC.getId()));
    }
}
