package org.itstep.busstop;

public class Passenger {
    private int timeArrived;

    public Passenger(int currentTime) {
        timeArrived = currentTime;
    }

    public int getTimeArrived() {
        return timeArrived;
    }
}
