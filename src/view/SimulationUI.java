package view;


import control.ActionManager;

import javax.swing.*;

public class SimulationUI {

    private JFrame frame;
    private DataPanel dataPanel;
    private ResultsPanel resultsPanel;
    private FirstPanel firstPanel;

    public SimulationUI(ActionManager actionManager) {
        frame = new JFrame("Mateus (F) - Stessens (H)");
        firstPanel = new FirstPanel(actionManager);
        dataPanel = new DataPanel(actionManager);
        resultsPanel = new ResultsPanel(actionManager);
//        frame.setIconImage(new ImageIcon(getClass().getResource("images/favicon.png")).getImage());
        //frame.add(dataPanel);
        frame.add(firstPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


    }


    public void showResultsPanel() {
        frame.remove(dataPanel);
        frame.add(resultsPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showDataPanel() {
        dataPanel.clear();
        frame.remove(firstPanel);
        frame.add(dataPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showFirstPanel() {
        dataPanel.clear();
        frame.remove(resultsPanel);
        frame.add(firstPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public float getClientsPerHour() {
        return dataPanel.getClientsPerHour();
    }

    public float getAttentionSpeed() {
        return dataPanel.getAttentionSpeed();
    }

    public float getInterruptionPerHour() {
        return dataPanel.getInterruptionPerHour();
    }

    public float getInterruptionFrequency() {
        return dataPanel.getInterruptionFrequency();
    }

    public double getDuration() {
        return dataPanel.getDuration();
    }


    public void setPercentageInService(double percentageInService) {
        resultsPanel.setPercentageInService(percentageInService);
    }

    public void setPercentageOutOfService(double percentageOutOfService) {
        resultsPanel.setPercentageOutOfService(percentageOutOfService);
    }

    public void setNoClientsInService(double noClientsInService) {
        resultsPanel.setNoClientsInService(noClientsInService);
    }

    public void setNoClientsOutService(double noClientsOutService) {
        resultsPanel.setNoClientsOutService(noClientsOutService);
    }

    public void setProbabilityOneClient(double probabilityOneClient) {
        resultsPanel.setProbabilityOneClient(probabilityOneClient);
    }

    public void setProbabilityTwoClients(double probabilityTwoClients) {
        resultsPanel.setProbabilityTwoClients(probabilityTwoClients);
    }

    public void setProbabilityThreeClients(double probabilityThreeClients) {
        resultsPanel.setProbabilityThreeClients(probabilityThreeClients);
    }

    public void setProbabilityFourClients(double probabilityFourClients) {
        resultsPanel.setProbabilityFourClients(probabilityFourClients);
    }

    public void setProbabilityFiveClients(double probabilityFiveClients) {
        resultsPanel.setProbabilityFiveClients(probabilityFiveClients);
    }

    public void setPercentageServingClients(double percentageServingClients) {
        resultsPanel.setPercentageServingClients(percentageServingClients);
    }

    public void setPercentageWithClientsAndOutOfService(double percentageWithClientsAndOutOfService) {
        resultsPanel.setPercentageWithClientsAndOutOfService(percentageWithClientsAndOutOfService);
    }

    public void setPercentageServedClients(double percentageServedClients) {
        resultsPanel.setPercentageServedClients(percentageServedClients);
    }

    public void setPercentageDesertion(double percentageDesertion) {
        resultsPanel.setPercentageDesertion(percentageDesertion);
    }

    public void setAverageSystemLength(double averageSystemLength) {
        resultsPanel.setAverageSystemLength(averageSystemLength);
    }

    public void setAverageQueueLength(double averageQueueLength) {
        resultsPanel.setAverageQueueLength(averageQueueLength);
    }

    public void setAverageTimeInSystem(double averageTimeInSystem) {
        resultsPanel.setAverageTimeInSystem(averageTimeInSystem);
    }

    public void setAverageTimeInQueue(double averageTimeInQueue) {
        resultsPanel.setAverageTimeInQueue(averageTimeInQueue);
    }

    public void setAverageTimeInChannel(double averageTimeInChannel) {
        resultsPanel.setAverageTimeInChannel(averageTimeInChannel);
    }

    public void setDesertion1(double desertion1) {
        resultsPanel.setPercentageDesertion1(desertion1);
    }

    public void setDesertion2(double desertion2) {
        resultsPanel.setPercentageDesertion2(desertion2);
    }

    public void setWastedTime(double wastedTime) {
        resultsPanel.setWastedTime(wastedTime);
    }
    public void setNotEntering(double notEntering) {
        resultsPanel.setNotEntering(notEntering);
    }

    public void setEntering(double notEntering) {
        resultsPanel.setEntering(notEntering);
    }

    public void setLambdaRaya(double lambdaRaya) {
        resultsPanel.setLambdaRaya(lambdaRaya);
    }

    public void setProbabilityZeroClient(double probabilityZeroClient) {
        resultsPanel.setProbabilityZeroClient(probabilityZeroClient);
    }

    public void setAverageTimeInterrupted(double averageTimeInterrupted) {
        resultsPanel.setAverageTimeInterrupted(averageTimeInterrupted);
    }

    public void setMu(double mu) {
        resultsPanel.setMu(mu);
    }

    public void setMur(double mur) {
        resultsPanel.setMur(mur);
    }

    public void setLambda(double lambda) {
        resultsPanel.setLambda(lambda);
    }

    public void setLambdar(double lambdar) {
        resultsPanel.setLambdar(lambdar);
    }

    public void setHs(double hs) {
        resultsPanel.setHs(hs);
    }

}
