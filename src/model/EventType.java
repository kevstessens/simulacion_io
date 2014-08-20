package model;

public enum EventType {

    INITIATION("INICIO"), ARRIVAL("ARRIBO"), DEPARTURE("SALIDA"), INTERRUPTION("INTERRUPCION"), REACTIVATION("REACTIVACION");
    private String name;

    EventType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
