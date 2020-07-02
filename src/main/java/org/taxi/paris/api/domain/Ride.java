package org.taxi.paris.api.domain;

import lombok.Getter;
import lombok.Setter;

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
}
