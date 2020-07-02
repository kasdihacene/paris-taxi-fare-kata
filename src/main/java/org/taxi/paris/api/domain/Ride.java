package org.taxi.paris.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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

    public LocalDateTime localDateTime() {
        return LocalDateTime.ofInstant(Instant.parse(this.startTime), ZoneId.of(ZoneOffset.UTC.getId()));
    }
}
