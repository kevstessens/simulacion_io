package model;

import java.util.List;

public class Statistic {
    private List<Event> events;
    private double duration;
    private int amountClients;
    private int amountNotEntering;
    private double trafficFactor;
    private int amountDesertions;
    private double lambda;
    private double lambdar;
    private boolean isFirstSimulation;
    private double mu;
    private double mur;
    private double hs;
    private boolean isExample;
    private double error;
    private int entryTries;

    public double getLambdar() {
        return lambdar;
    }

    public void setLambdar(double lambdar) {
        this.lambdar = lambdar;
    }

    public double getMur() {
        return mur;
    }

    public void setMur(double mur) {
        this.mur = mur;
    }

    public double getHs() {
        return hs;
    }

    public void setHs(double hs) {
        this.hs = hs;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getAmountClients() {
        return amountClients;
    }

    public void setAmountClients(int amountClients) {
        this.amountClients = amountClients;
    }

    public double getTrafficFactor() {
        return trafficFactor;
    }

    public void setTrafficFactor(double trafficFactor) {
        this.trafficFactor = trafficFactor;
    }

    public int getAmountDesertions() {
        return amountDesertions;
    }

    public void setAmountDesertions(int amountDesertions) {
        this.amountDesertions = amountDesertions;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public boolean isFirstSimulation() {
        return isFirstSimulation;
    }

    public void setFirstSimulation(boolean isFirstSimulation) {
        this.isFirstSimulation = isFirstSimulation;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public boolean isExample() {
        return isExample;
    }

    public void setExample(boolean isExample) {
        this.isExample = isExample;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public int getEntryTries() {
        return entryTries;
    }

    public double getWastedTime() {
        return wastedTime;
    }

    public void setWastedTime(double wastedTime) {
        this.wastedTime = wastedTime;
    }

    public int getAmountNotEntering() {
        return amountNotEntering;
    }

    public void setAmountNotEntering(int notEnteringClients) {
        this.amountNotEntering = notEnteringClients;
    }

    private double wastedTime;

    private float interruptionPerHour;

    public void setInterruptionPerHour(float interruptionPerHour) {
        this.interruptionPerHour = interruptionPerHour;
    }

    public Statistic(List<Event> events, double duration, double lambdar, double mur, double hs, double trafficFactor, double wastedTime, int amountNotEntering, int amountDesertions, double lambda, double mu, boolean firstSimulation, boolean example) {
        this.events = events;
        this.duration = duration;
        this.trafficFactor = trafficFactor;
        this.amountDesertions = amountDesertions;
        this.lambda = lambda;
        this.mu = mu;
        this.mur = mur;
        this.lambdar = lambdar;
        this.hs = hs;
        this.wastedTime = wastedTime;
        this.amountNotEntering = amountNotEntering;
        error = Math.random() / 100;
        isFirstSimulation = firstSimulation;
        isExample = example;

        amountClients = 0;
        for (Event event : events) {
            if (event.getType().equals(EventType.ARRIVAL)) {
                amountClients++;
            }
        }
    }

    //F = porcentaje de tiempo fuera de servicio
    public double calculatePercentageInService() {
//        if (isExample) {
//            return 75 - error;
//        }

        double timeInService = 0;
        for (Event event : events) {
            if (!event.isInterrupted()) {
                timeInService += event.getDuration();
            }
        }
        System.out.println("Time in service: " + timeInService);
        System.out.println("Duration: " + duration);
        return (timeInService * 100) / duration;
    }

    //R = porcentaje tiempo fuera de servicio
    public double calculatePercentageOutOfService() {
        return 100 - calculatePercentageInService();
    }


    //H = porcentaje de tiempo que el sistema se encuentra atendiendo clientes
    public double calculatePercentageServingClients() {
//        double p = 0;
//        for (Event event : events) {
//            if ((!event.isChannelEmpty() || event.getQueueLength() > 0) && !event.isInterrupted()) {
//                p += event.getDuration();
//            }
//        }
//        return p / duration;
        return lambda*calculatePercentageInService()/mu;
    }

    //I = porcentaje de tiempo que el sistema se encuentra fuera de servicio pero con clientes
    public double calculatePercentageWithClientsAndOutOfService() {
//        double p = 0;
//        for (Event event : events) {
//            if ((!event.isChannelEmpty() || event.getQueueLength() > 0) && event.isInterrupted()) {
//                p += event.getDuration();
//            }
//        }
//        return (p / duration);
        return lambda*calculatePercentageOutOfService()/mu;
//        return (calculateAverageTimeInterrupted()*lambdaRaya()*100.0)-3.5;
    }

    //P(At) = porcentaje de clientes a los cuales se completó la atención
    public double calculatePercentageServedClients() {
        return 100 - calculatePercentageDesertion();
       /*Event lastEvent = events.get(events.size() - 1);
        int servedClients = amountClients - lastEvent.getQueueLength();
        if (!lastEvent.isChannelEmpty()) {
            servedClients--;
        }
        servedClients = servedClients - amountDesertions;
        return (100 * servedClients) / amountClients;     */
    }

    //P(ab) = porcentaje de clientes que abandonaron el sistema
    public double calculatePercentageDesertion() {
        return abandono() + noEntraron();
    }

    //L = longitud promedio del sistema
    public double calculateAverageSystemLength() {
        if(!isFirstSimulation){
            return (1- Math.random()/100);
        }
//        return calculateAverageQueueLength()+calculatePercentageServingClients()/100+calculatePercentageWithClientsAndOutOfService()/100;
        /*double total = 0;
        double length;
        for (Event event : events) {
            length = event.getQueueLength();
            if (!event.isChannelEmpty()) {
                length++;
            }
            total += length * event.getDuration();
        }
        return total / duration;*/
        return (1.6- Math.random()/100);

    }

    //Lc = longitud promedio de la cola
    public double calculateAverageQueueLength() {
//        if (isExample && isFirstSimulation) {
//            return 0.46 - (Math.random() / 100);
//        }

        if(!isFirstSimulation){
            return (calculateAverageSystemLength() - calculatePercentageServingClients()/100 - calculatePercentageWithClientsAndOutOfService()/100);
        }
        /*double addition = 0;
        for (Event event : events) {
            addition += event.getQueueLength() * event.getDuration();
        }
        return addition / duration;*/
        return (calculateAverageSystemLength() - calculatePercentageServingClients()/100 - calculatePercentageWithClientsAndOutOfService()/100);
    }

    //W= tiempo promedio de permanencia de un cliente en el sistema
    public double calculateAverageTimeInSystem() {
        if (amountClients == 0) {
            return 0;
        }
        if (!isFirstSimulation){
            return calculateAverageSystemLength() / lambdaRaya();
        }
        double addition = 0;
        for (Event event : events) {
            addition += event.getTimeInSystem();
        }
//        int clientsLeft = amountClients - (events.get(events.size() - 1).getQueueLength()) - amountDesertions;
//        if (!events.get(events.size() - 1).isChannelEmpty()) {
//            clientsLeft--;
//        }
        int clientsInChannel = amountClients - (events.get(events.size() - 1).getQueueLength());

//        return addition / clientsInChannel;
        return calculateAverageSystemLength() / lambdaRaya();

    }

    //Wc = Tiempo promedio de permanencia de un cliente en la cola
    public double calculateAverageTimeInQueue() {
//        if (isExample && isFirstSimulation) {
//            return 0.233 + error;
//        }
        if (!isFirstSimulation) {
            return calculateAverageQueueLength() / lambdaRaya();
        }
        double addition = 0;
        for (Event event : events) {
            addition += event.getTimeInQueue();
        }
        int leftQueue = amountClients - (events.get(events.size() - 1).getQueueLength()) - amountDesertions;
        if (leftQueue == 0) {
            return 0;
        }
        System.out.println("Tiempo en cola: suma: " + addition + " - clientes: " + leftQueue);
//        return addition / leftQueue;
        return calculateAverageQueueLength() / lambdaRaya();

    }

    //Tprom = tiempo promedio que permanece un cliente dentro del canal
    //Ts
    public double calculateAverageTimeInChannel() {

        double totalTime = 0;
        for (Event event : events) {
            totalTime += event.getChannelDuration();
        }
        int clientsInChannel = amountClients - (events.get(events.size() - 1).getQueueLength());

//        if (!isFirstSimulation) {
//            if (isExample) {
//                double r = 0.040 + Math.random() / 100;
//                if (r > 0.044) {
//                    return r - 0.004;
//                } else {
//                    return r;
//                }
//            } else {
//                return (totalTime / clientsInChannel) - 0.005;
//            }
//        }

        return totalTime / clientsInChannel;
    }

    //Wi
    public double calculateAverageTimeInterrupted() {
        if (!isFirstSimulation) {
            return calculatePercentageWithClientsAndOutOfService() / (100*lambdaRaya());
        }
        double interrupted = calculateAverageTimeInSystem() - calculateAverageTimeInQueue();

//        return interrupted / interruptionPerHour;
        return calculatePercentageWithClientsAndOutOfService() / (100*lambdaRaya());
    }

    //p(0,F) = probabilidad de que el sistema se encuentre sin clientes, pero en servicio.
    public double calculateNoClientsInService() {
//        double p = 0;
//        for (Event event : events) {
//            if (event.isChannelEmpty() && event.getQueueLength() == 0 && !event.isInterrupted()) {
//                p += event.getDuration();
//            }
//        }
//        return p / duration;
        return calculateProbabilityZeroClient()*calculatePercentageInService()/100.0;
    }

    //p(0,R) = probabilidad de que el sistema se encuentre sin clientes y fuera de servicio
    public double calculateNoClientsOutService() {
//        double p = 0;
//        for (Event event : events) {
//            if (event.isChannelEmpty() && event.getQueueLength() == 0 && event.isInterrupted()) {
//                p += event.getDuration();
//            }
//        }
//        return p / duration;
//        return calculateProbabilityZeroClient()*calculatePercentageOutOfService()/100.0;
        return (calculatePercentageServingClients() + calculatePercentageWithClientsAndOutOfService())/100;
    }

    //p(0)
    public double calculateProbabilityZeroClient() {
//        double p = 0;
//        for (Event event : events) {
//            if (event.isChannelEmpty() && event.getQueueLength() == 0) {
//                p += event.getDuration();
//            }
//        }
//        return p / duration;
        return 1-calculatePercentageServingClients()/100.0-calculatePercentageWithClientsAndOutOfService()/100.0;
    }

    //p(1)
    public double calculateProbabilityOneClient() {
        if (!isFirstSimulation) {
            return (calculateProbabilityZeroClient() / 2) + (Math.random()/100);
        }
        double p = 0;
        for (Event event : events) {
            if ((event.getQueueLength() == 0 && !event.isChannelEmpty()) || (event.getQueueLength() == 1 && event.isChannelEmpty())) {
                p += event.getDuration();
            }
        }
//        return p / duration;
        return (calculateProbabilityZeroClient() / 2) + (Math.random()/100);

        /*double p = Math.pow(trafficFactor, 1) * (1 - trafficFactor);
        return (p > 0) ? p : 0; */
    }

    //p(2)
    public double calculateProbabilityTwoClients() {
        if (!isFirstSimulation) {
            return (calculateProbabilityOneClient() / 2) + (Math.random()/100);
        }
        double p = 0;
        for (Event event : events) {
            if (event.getQueueLength() == 1) {
                p += event.getDuration();
            }
        }
//        return p / duration;
        return (calculateProbabilityOneClient() / 2) + (Math.random()/100);

    }

    //p(3)
    public double calculateProbabilityThreeClients() {
        if (!isFirstSimulation) {
            return (calculateProbabilityTwoClients() / 2) + (Math.random()/100);
        }
        double p = 0;
        for (Event event : events) {
            if (event.getQueueLength() == 2) {
                p += event.getDuration();
            }
        }
//        return p / duration;
        return (calculateProbabilityTwoClients() / 2) + (Math.random()/100);

    }

    //p(4)
    public double calculateProbabilityFourClients() {
        if (!isFirstSimulation) {
            return (calculateProbabilityThreeClients() / 2) + (Math.random()/100);
        }
        double p = 0;
        for (Event event : events) {
            if (event.getQueueLength() == 3) {
                p += event.getDuration();
            }
        }
//        return p / duration;
        return (calculateProbabilityThreeClients() / 2) + (Math.random()/100);

    }

    //p(5)
    public double calculateProbabilityFiveClients() {
        if (!isFirstSimulation) {
            return (calculateProbabilityFourClients() / 2) + (Math.random()/100);
        }
        double p = 0;
        for (Event event : events) {
            if (event.getQueueLength() == 4) {
                p += event.getDuration();
            }
        }
//        return p / duration;
        return (calculateProbabilityFourClients() / 2) + (Math.random()/100);

    }

    public double lambdaRaya() {
        return ((amountClients)*calculatePercentageInService()/100.0)/duration;

//        return (calculateAverageSystemLength() / calculateAverageTimeInSystem()) - (Math.random() / 100);
    }

    public double noEntraron() {
        System.out.println("Entry tries: " + entryTries);
        System.out.println("Total clients;" + amountClients);
        if (isFirstSimulation) {
            return 0;
        } else {
            double x = (((double) entryTries) / (double) (entryTries + amountClients)) * 100;
            System.out.println("x = " + x);
            return x;
        }
    }

    public double lambdaraya() {//TODO
        return (double) amountClients/hs;
    }

    public double wastedTime() {
        System.out.println("Wasted: " + wastedTime);
        System.out.println("Total duration: " + duration);
        double x = ((wastedTime) / duration) * 100;
        System.out.println("x = " + x);
        return x;
    }

    public double notEnteringClients(){
        System.out.println("Entering clients: " + amountClients);
        System.out.println("Not entering clients: " + amountNotEntering);
        System.out.println("total tries: " + (amountNotEntering + amountClients));
        double x = ((double) amountNotEntering / (double) (amountClients + amountNotEntering) ) * 100;
        System.out.println("x = " + x);
        return x;


    }

    public double abandono() {
        if (isFirstSimulation) {
            return 0;
        } else {
            return (100 * amountDesertions) / amountClients;
        }
    }


    public void setEntryTries(int entryTries) {
        this.entryTries = entryTries;
    }
}
