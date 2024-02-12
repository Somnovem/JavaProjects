package org.itstep.busstop;

import java.util.ArrayDeque;
import java.util.Random;

public class BusStop  implements Runnable{

    private int peopleStart = 6;
    private int peopleArrivingMin = 1;
    private int peopleArrivingMax = 10;
    private int busSeatsMin = 2;
    private int busSeatsMax = 8;
    private int testTime = 10;
    private int workerRate = 1000;


    private int totalMinutesWaited = 0;
    private int totalPassengersSeated = 0;

    Random random = new Random();

    private int getRandom(int min, int max) {
        return random.nextInt(max-min)+min;
    }

    private void passengersArrive(int time) {
        int peopleArrived = getRandom(peopleArrivingMin, peopleArrivingMax);
        for (int i = 0; i < peopleArrived; i++) {
            passengers.push(new Passenger(time));
        }
    }

    private void busArrives(int time) {
        int seats = getRandom(busSeatsMin, busSeatsMax);
        for (int i = 0; i < seats && passengers.peek() != null; i++) {
            totalMinutesWaited += time - passengers.pop().getTimeArrived();
            totalPassengersSeated++;
        }
    }

    private ArrayDeque<Passenger> passengers = new ArrayDeque<>();

    private void init() {
        for (int i = 0; i < peopleStart; i++){
            passengers.push(new Passenger(0));
        }
    }

    @Override
    public void run() {
        init();
        for (int currentTime = 0; currentTime < testTime; currentTime++){
            passengersArrive(currentTime);
            busArrives(currentTime);
        }
        printStatistics();
    }

    private void printStatistics() {
        System.out.println("Test duration: " + testTime + " minutes");
        System.out.println("Total passengers seated: " + totalPassengersSeated);
        System.out.println("Missed work: " + passengers.size());
        System.out.println("Average waiting time: " + (totalMinutesWaited / totalPassengersSeated));
        double losses = ((double) (workerRate * totalMinutesWaited) / 60) + passengers.size()*workerRate*8;
        System.out.println("Total losses suffered(per working rate at " + workerRate + "): " + losses);
    }
}
