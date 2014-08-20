package control;

import model.*;
import view.SimulationUI;

import javax.swing.*;

public class SimulationController {
    private SimulationInt simulation;
    private ActionManager actionManager;
    private SimulationUI simulationUI;
    private boolean isFirstSimulation;


    public SimulationController() {
        actionManager = new ActionManager();
    }

    public void init() {
        actionManager.init(this);
        simulationUI = new SimulationUI(actionManager);
    }

    public void submitData() throws NumberFormatException {

        float clientsPerHour = simulationUI.getClientsPerHour();
        float attentionSpeed = simulationUI.getAttentionSpeed();
        float interruptionPerHour = simulationUI.getInterruptionPerHour();
        float interruptionFrequency = simulationUI.getInterruptionFrequency();
        double duration = simulationUI.getDuration();

        if (isFirstSimulation) {
            simulation = new Simulation(clientsPerHour, attentionSpeed, interruptionPerHour, interruptionFrequency, duration);
            System.out.println("simulation ONE was created");
        } else {
            simulation = new SimulationTwo(clientsPerHour, attentionSpeed, interruptionPerHour, interruptionFrequency, duration);
            System.out.println("simulation ONE was created");
        }

    }

    public void simulate() {
        simulation.simulate();

        Statistic statistic = new Statistic(simulation.getEvents(), simulation.getDuration(),simulation.getInterruptionAverageDuration(), simulation.getInterruptionPerHour(), simulation.getDuration(), simulation.getTrafficFactor(),simulation.getWastedTime(), simulation.getAmountNotEntering(), simulation.getAmountDesertions(), simulation.getLambda(), simulation.getMu(), isFirstSimulation, simulation.isExample());
        statistic.setInterruptionPerHour(simulationUI.getInterruptionPerHour());
        if(!isFirstSimulation){
            statistic.setEntryTries(simulation.getEntryTries());
        }

        simulationUI.setAverageQueueLength(round(statistic.calculateAverageQueueLength()));
        simulationUI.setAverageSystemLength(round(statistic.calculateAverageSystemLength()));

        simulationUI.setProbabilityZeroClient(round(statistic.calculateProbabilityZeroClient()));
        simulationUI.setProbabilityOneClient(round(statistic.calculateProbabilityOneClient()));
        simulationUI.setProbabilityTwoClients(round(statistic.calculateProbabilityTwoClients()));
        simulationUI.setProbabilityThreeClients(round(statistic.calculateProbabilityThreeClients()));
        simulationUI.setProbabilityFourClients(round(statistic.calculateProbabilityFourClients()));
        simulationUI.setProbabilityFiveClients(round(statistic.calculateProbabilityFiveClients()));

        simulationUI.setAverageTimeInChannel(round(statistic.calculateAverageTimeInChannel() + statistic.calculateAverageTimeInterrupted()));
        simulationUI.setAverageTimeInQueue(round(statistic.calculateAverageTimeInQueue()));
        simulationUI.setAverageTimeInSystem(round(statistic.calculateAverageTimeInSystem()));
        simulationUI.setPercentageServedClients(round(statistic.calculatePercentageServedClients()));
        simulationUI.setPercentageDesertion(round(statistic.calculatePercentageDesertion()));
        simulationUI.setPercentageServingClients(round(statistic.calculatePercentageServingClients()));
        simulationUI.setPercentageWithClientsAndOutOfService(round(statistic.calculatePercentageWithClientsAndOutOfService()));
        simulationUI.setPercentageInService(round(statistic.calculatePercentageInService()));
        simulationUI.setPercentageOutOfService(round(statistic.calculatePercentageOutOfService()));
        simulationUI.setNoClientsInService(round(statistic.calculateNoClientsInService()));
        simulationUI.setNoClientsOutService(round(statistic.calculateNoClientsOutService()));

        simulationUI.setDesertion1(round(statistic.abandono()));
        simulationUI.setDesertion2(round(statistic.noEntraron()));
        simulationUI.setWastedTime(round(statistic.wastedTime()));
        simulationUI.setNotEntering(round(statistic.notEnteringClients()));
        simulationUI.setEntering(round(statistic.notEnteringClients()));
//        simulationUI.setLambdaRaya(round(statistic.lambdaraya()));
        simulationUI.setLambdaRaya(round(statistic.lambdaRaya()));
        simulationUI.setAverageTimeInterrupted(round(statistic.calculateAverageTimeInterrupted()));

        simulationUI.setMu(statistic.getMu());
        simulationUI.setLambda(statistic.getLambda());
        simulationUI.setMur(statistic.getMur());
        simulationUI.setLambdar(statistic.getLambdar());
        simulationUI.setHs(statistic.getHs());

        simulationUI.showResultsPanel();
    }

    private double round(double v) {
        int i = (int) (v * 1000);
        String s = "" + (int) (v * 10000);
        char a = s.charAt(s.length() - 1);
        if (Integer.parseInt(a + "") < 5) {
            return ((double) i) / 1000;
        } else {
            return (((double) (i + 1)) / 1000);
        }

    }

    public void createExcel() {
        String name = askForName();
        ExcelGenerator.generateExcel(name, simulation.getEvents());
    }

    public void startNewSimulation() {
        simulationUI.showFirstPanel();
    }

    private static String askForName() {
        String name;
        try {
            name = JOptionPane.showInputDialog(null, "Nombre Archivo Excel", "Ingrese Nombre", JOptionPane.QUESTION_MESSAGE);
        } catch (NumberFormatException e) {
            name = askForName();
        }
        return name;
    }

    public void selectFirstSimulation() {
        isFirstSimulation = true;
        simulationUI.showDataPanel();
    }

    public void selectSecondSimulation() {
        isFirstSimulation = false;
        simulationUI.showDataPanel();
    }
}
