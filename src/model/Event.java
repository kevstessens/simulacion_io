package model;


public class Event {

    private double duration;
    private double startTime;

    //arrivals
    private int clientNumberArrival;
    private double iac;
    private double nextArrival;

    // queue
    private int queueLength;
    private double timeInQueue;

    //channel
    private boolean channelIsEmpty;
    private int clientNumberChannel;
    private double durationChannel;
    private double end;

    //departure
    private int clientNumberDeparture;
    private double timeInSystem;

    //interruption
    private boolean interrupted;
    private double interruptionDuration;
    private double nextInterruption;

    //type
    private EventType type;


    public Event(double startTime, EventType type) {
        this.startTime = startTime;
        this.type = type;
    }

    public Event(double startTime, EventType type, int length, boolean channelIsEmpty) {
        this.startTime = round(startTime);
        this.type = type;
        this.queueLength = length;
        this.channelIsEmpty = channelIsEmpty;
    }

    public EventType getType() {
        return type;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = round(duration);
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = round(startTime);
    }

    public int getClientNumberArrival() {
        return clientNumberArrival;
    }

    public void setClientNumberArrival(int clientNumberArrival) {
        this.clientNumberArrival = clientNumberArrival;
    }

    public double getIac() {
        return iac;
    }

    public void setIac(double iac) {
        this.iac = round(iac);
    }

    public double getNextArrival() {
        return nextArrival;
    }

    public void setNextArrival(double nextArrival) {
        this.nextArrival = round(nextArrival);
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public boolean isChannelEmpty() {
        return channelIsEmpty;
    }

    public void setChannelIsEmpty(boolean channelIsEmpty) {
        this.channelIsEmpty = channelIsEmpty;
    }

    public int getClientNumberChannel() {
        return clientNumberChannel;
    }

    public void setClientNumberChannel(int clientNumberChannel) {
        this.clientNumberChannel = clientNumberChannel;
    }

    public double getChannelDuration() {
        return durationChannel;
    }

    public void setChannelDuration(double durationChannel) {
        this.durationChannel = round(durationChannel);
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = round(end);
    }

    public int getClientNumberDeparture() {
        return clientNumberDeparture;
    }

    public void setClientNumberDeparture(int clientNumberDeparture) {
        this.clientNumberDeparture = clientNumberDeparture;
    }

    public double getTimeInSystem() {
        return timeInSystem;
    }

    public void setTimeInSystem(double timeInSystem) {
        this.timeInSystem = round(timeInSystem);
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    public double getInterruptionDuration() {
        return interruptionDuration;
    }

    public void setInterruptionDuration(double interruptionDuration) {
        this.interruptionDuration = round(interruptionDuration);
    }

    public void setNextInterruption(double nextInterruption) {
        this.nextInterruption = round(nextInterruption);
    }

    public double getNextInterruption() {
        return nextInterruption;
    }

    private double round(double n) {
        int nInt = (int) (n * 1000);
        return ((double) nInt) / 1000;
    }

    public double getTimeInQueue() {
        return timeInQueue;
    }

    public void setTimeInQueue(double timeInQueue) {
        this.timeInQueue = round(timeInQueue);
    }
}
