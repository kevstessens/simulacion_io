package model;

import sun.misc.Queue;

import java.util.ArrayList;
import java.util.List;

//TOM
public class SimulationTwo implements SimulationInt {

    private List<Event> events;

    private double clientsPerHour;       //lambda
    private double attentionAverageSpeed;     //mu
    private double interruptionPerHour;    //lambda r
    private double interruptionAverageDuration;     //mu r
    private double duration;     //Hs


    private double trafficFactor;

    private double time;
    private double nextArrival;
    private double nextDeparture;
    private double nextInterruption;

    private boolean isInterrupted;
    private double interruptionEnd;

    private int eventPosition;
    private int clientNumberChannel;

    private int amountDesertions;
    private int amountNotEntering = 0;
    private double wastedTime = 0;


    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public double getClientsPerHour() {
        return clientsPerHour;
    }

    public void setClientsPerHour(double clientsPerHour) {
        this.clientsPerHour = clientsPerHour;
    }

    public double getAttentionAverageSpeed() {
        return attentionAverageSpeed;
    }

    public void setAttentionAverageSpeed(double attentionAverageSpeed) {
        this.attentionAverageSpeed = attentionAverageSpeed;
    }

    public double getInterruptionPerHour() {
        return interruptionPerHour;
    }

    public void setInterruptionPerHour(double interruptionPerHour) {
        this.interruptionPerHour = interruptionPerHour;
    }

    public double getInterruptionAverageDuration() {
        return interruptionAverageDuration;
    }

    public void setInterruptionAverageDuration(double interruptionAverageDuration) {
        this.interruptionAverageDuration = interruptionAverageDuration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setTrafficFactor(double trafficFactor) {
        this.trafficFactor = trafficFactor;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getNextArrival() {
        return nextArrival;
    }

    public void setNextArrival(double nextArrival) {
        this.nextArrival = nextArrival;
    }

    public double getNextDeparture() {
        return nextDeparture;
    }

    public void setNextDeparture(double nextDeparture) {
        this.nextDeparture = nextDeparture;
    }

    public double getNextInterruption() {
        return nextInterruption;
    }

    public void setNextInterruption(double nextInterruption) {
        this.nextInterruption = nextInterruption;
    }

    public boolean isInterrupted() {
        return isInterrupted;
    }

    public void setInterrupted(boolean isInterrupted) {
        this.isInterrupted = isInterrupted;
    }

    public double getInterruptionEnd() {
        return interruptionEnd;
    }

    public void setInterruptionEnd(double interruptionEnd) {
        this.interruptionEnd = interruptionEnd;
    }

    public int getEventPosition() {
        return eventPosition;
    }

    public void setEventPosition(int eventPosition) {
        this.eventPosition = eventPosition;
    }

    public int getClientNumberChannel() {
        return clientNumberChannel;
    }

    public void setClientNumberChannel(int clientNumberChannel) {
        this.clientNumberChannel = clientNumberChannel;
    }

    public void setAmountDesertions(int amountDesertions) {
        this.amountDesertions = amountDesertions;
    }

    public int getAmountNotEntering() {
        return amountNotEntering;
    }

    public double getWastedTime() {
        return wastedTime;
    }

    public void setWastedTime(double wastedTime) {
        this.wastedTime = wastedTime;
    }

    public SimulationTwo(double clientsPerHour, double attentionAverageSpeed,
                      double interruptionPerHour, double interruptionAverageDuration, double duration) {
        this.clientsPerHour = clientsPerHour;
        this.attentionAverageSpeed = attentionAverageSpeed;
        this.interruptionPerHour = interruptionPerHour;
        this.interruptionAverageDuration = interruptionAverageDuration;
        this.duration = duration;
        trafficFactor = clientsPerHour / attentionAverageSpeed;
        events = new ArrayList<Event>();
        amountDesertions = 0;
    }


    public void simulate() {
        time = 0;
        isInterrupted = false;
        interruptionEnd = 0;
        nextInterruption = time + getInterruption();
        nextDeparture = 0;
        eventPosition = 0;

        int clientNumber = 0;
        clientNumberChannel = 0;
        double iac = getIAC();
        int length = 0;
        boolean channelIsEmpty = true;

        //initiation
        Event event = new Event(time, EventType.INITIATION, length, channelIsEmpty);
        event.setIac(iac);
        event.setNextArrival(time + iac);
        event.setDuration(Math.min(time + iac, nextInterruption));
        event.setNextInterruption(nextInterruption);

        events.add(event);
        nextArrival = time + iac;

        while (time < duration) {

            System.out.println("Event nº: " + eventPosition);
            System.out.println("\t time : " + time);

            System.out.println("\t next interruption: " + nextInterruption);
            System.out.println("\t next arrival: " + nextArrival);
            System.out.println("\t next depature: " + nextDeparture);
            System.out.println("\t interrupted? :" + isInterrupted);
            System.out.println("\t interruption end: " + interruptionEnd);


            Event lastEvent = events.get(eventPosition);
            if (isInterrupted) {
                if (nextArrival < interruptionEnd) {
                    amountNotEntering++;
                    addReactivationEvent(lastEvent);
                    eventPosition++;
                } else {
                    addReactivationEvent(lastEvent);
                    eventPosition++;
                }
            } else if (nextInterruption <= nextArrival && (nextInterruption <= nextDeparture || lastEvent.isChannelEmpty())) {
                addInterruptionEvent(lastEvent);
                eventPosition++;
            } else if (nextArrival < nextInterruption && (nextArrival <= nextDeparture || lastEvent.isChannelEmpty())) {
                clientNumber++;
                addArrivalEvent(lastEvent, clientNumber);
                eventPosition++;
            } else if (nextDeparture < nextArrival && nextDeparture < nextInterruption) {
                addDepartureEvent(lastEvent);
                eventPosition++;
            } else {
                System.out.println("ELSEEE!!");
            }

        }

        Event lastEvent = events.get(events.size() - 1);
        lastEvent.setDuration(duration - lastEvent.getStartTime());

        System.out.println("Simulation has ended");

    }

    private void addArrivalEvent(Event lastEvent, int clientNumber) {

        System.out.println("\tARRIVAL");

        time += lastEvent.getDuration();

        double iac = getIAC();
        int length = 0;
        boolean isChannelEmpty = false;
        int clientNumberChan = 0;
        double durationChannel = 0;
        double end = 0;
        boolean zeroDuration = nextArrival == nextInterruption;


        if (!lastEvent.isChannelEmpty()) {
            length = lastEvent.getQueueLength() + 1;
        } else {
            clientNumberChan = clientNumber;
            clientNumberChannel++;
            durationChannel = getDurationChannel();

            if (isInterrupted) {
                nextDeparture = interruptionEnd + durationChannel;
            } else {
                nextDeparture = time + durationChannel;
            }

            end = nextDeparture;
        }

        Event event = new Event(time, EventType.ARRIVAL, length, isChannelEmpty);
        event.setClientNumberArrival(clientNumber);
        event.setIac(iac);
        event.setNextArrival(time + iac);
        event.setQueueLength(length);
        event.setChannelIsEmpty(isChannelEmpty);
        event.setClientNumberChannel(clientNumberChan);
        event.setChannelDuration(durationChannel);
        event.setEnd(end);
        event.setInterrupted(lastEvent.isInterrupted());

        nextArrival = time + iac;
        if (isInterrupted) {
            event.setDuration(Math.min(nextDeparture, Math.min(nextArrival, interruptionEnd)) - time);
        } else {
            event.setDuration(Math.min(nextDeparture, Math.min(nextArrival, nextInterruption)) - time);
        }

        if (event.getDuration() == 0 && !zeroDuration) {
            event.setDuration(0.001);
        }


        if (time < duration) {
            events.add(event);
        } else {
            System.out.println("END. time = " + time);
        }

    }

    private void addDepartureEvent(Event lastEvent) {
        System.out.println("\tDEPARTURE");

        time += lastEvent.getDuration();

        Event event;


        if (lastEvent.getQueueLength() == 0) {

            event = new Event(time, EventType.DEPARTURE, 0, true);

            nextDeparture = 0;

            event.setDuration(Math.min(nextArrival, nextInterruption) - time);

        } else {

            int length = lastEvent.getQueueLength() - 1;
            event = new Event(time, EventType.DEPARTURE, length, false);

            double channelDuration = getDurationChannel();

            clientNumberChannel++;

            event.setClientNumberChannel(clientNumberChannel);
            event.setChannelDuration(channelDuration);
            event.setEnd(time + channelDuration);

            nextDeparture = time + channelDuration;

            for (int i = events.size() - 1; i >= 0; i--) {
                Event e = events.get(i);
                if (e.getClientNumberArrival() == clientNumberChannel) {
                    event.setTimeInQueue(time - e.getStartTime());
                }
            }

            event.setDuration(Math.min(Math.min(nextArrival, nextInterruption), nextDeparture) - time);
        }

        if (event.getDuration() == 0) {
            event.setDuration(0.001);
        }

        for (int i = eventPosition; i >= 0; i--) {
            Event e = events.get(i);
            if (e.getClientNumberChannel() != 0) {
                event.setClientNumberDeparture(e.getClientNumberChannel());
                break;
            }
        }


        for (Event hEvent : events) {
            if (hEvent.getClientNumberArrival() == event.getClientNumberDeparture()) {
                event.setTimeInSystem(time - hEvent.getStartTime());
                break;
            }
        }

        if (time < duration) {
            events.add(event);
        } else {
            System.out.println("END. time = " + time);
        }

    }

    private void addInterruptionEvent(Event lastEvent) {

        boolean zeroDuration = nextInterruption == nextArrival;

        System.out.println("\tINTERRUPTION");

        time += lastEvent.getDuration();
        isInterrupted = true;
        double interruptionDuration = getInterruptionDuration();


        Event event = new Event(time, EventType.INTERRUPTION, lastEvent.getQueueLength(), lastEvent.isChannelEmpty());
        event.setInterrupted(true);
        event.setInterruptionDuration(interruptionDuration);

        interruptionEnd = time + interruptionDuration;
        //nextInterruption = time + getInterruption();

        if (nextDeparture != 0) {
            nextDeparture += interruptionDuration;
        }

        event.setDuration(Math.min(interruptionDuration, (nextArrival - time)));

        if (duration == 0 && !zeroDuration) {
            event.setDuration(0.001);
        }

        if (time < duration) {
            events.add(event);
        } else {
            System.out.println("END. time = " + time);
        }

    }

    private void addReactivationEvent(Event lastEvent) {

        boolean zeroDuration = interruptionEnd == nextArrival || interruptionEnd == nextDeparture;

        System.out.println("\tREACTIVATION");

        time = interruptionEnd;

        Event event = new Event(time, EventType.REACTIVATION, lastEvent.getQueueLength(), lastEvent.isChannelEmpty());

        nextInterruption = getInterruption() + time;
        isInterrupted = false;
        event.setNextInterruption(nextInterruption);
        event.setInterrupted(false);

        if (event.getDuration() == 0 && !zeroDuration) {
            event.setDuration(0.001);
        }

        if (nextDeparture != 0) {
            event.setDuration(Math.min(nextArrival, Math.min(nextDeparture, nextInterruption)) - time);
        } else {
            event.setDuration(Math.min(nextArrival, nextInterruption) - time);
        }


        if (time < duration) {
            events.add(event);
        } else {
            System.out.println("END. time = " + time);
        }

    }

    //simular el intervalo de tiempo entre arribos de clientes en h/cliente
    private double getIAC() {
        double iac = getThreeDecimals((1 / clientsPerHour) * (Math.log(1 / Math.random())));
        if (iac <= 0.001) {
            return getIAC();
        } else {
            return iac;
        }
    }

    //simular la duración del servicio en h/cliente
    private double getDurationChannel() {
        double dur = getThreeDecimals((1 / attentionAverageSpeed) * (Math.log(1 / Math.random())));
        if (dur <= 0.001) {
            return getDurationChannel();
        } else {
            return dur;
        }
    }

    //simular intervalo de tiempo entre interrupciones
    private double getInterruption() {
        double inter = getThreeDecimals((1 / interruptionPerHour) * (Math.log(1 / Math.random())));
        if (inter <= 0.001) {
            return getInterruption();
        } else {
            return inter;
        }
    }

    //simular la duración promedio de la interrupción
    private double getInterruptionDuration() {
        double dur = getThreeDecimals((1 / interruptionAverageDuration) * (Math.log(1 / Math.random())));
        if (dur <= 0.001) {
            return getInterruptionDuration();
        } else {
            return dur;
        }
    }

    private double getThreeDecimals(double n) {
        int nInt = (int) (n * 1000);
        return ((double) nInt) / 1000;
    }

    public List<Event> getEvents() {
        return events;
    }

    public double getDuration() {
        return duration;
    }

    public double getTrafficFactor() {
        return trafficFactor;
    }

    public int getAmountDesertions() {
        return amountDesertions;
    }

    public double getLambda() {
        return clientsPerHour;
    }

    public double getMu() {
        return attentionAverageSpeed;
    }

    public boolean isExample() {
        return (clientsPerHour == 10 && attentionAverageSpeed == 20 && interruptionPerHour==3 && interruptionAverageDuration==9 && duration >= 5000);
    }

    public int getEntryTries() {
        return 0;
    }
}
