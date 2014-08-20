package model;


import java.util.List;

public interface SimulationInt {

    void simulate();

    List<Event> getEvents();

    double getDuration();

    double getTrafficFactor();

    int getAmountDesertions();

    double getLambda();

    double getMu();
    double getWastedTime();
    int getAmountNotEntering();
    double getInterruptionPerHour();
    double getInterruptionAverageDuration();

    boolean isExample();

    int getEntryTries();
}
