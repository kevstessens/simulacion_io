package view;

import control.ActionManager;

import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel {
    private JLabel percentageInService;
    private JLabel percentageOutOfService;

    private JLabel mu;
    private JLabel mur;
    private JLabel lambda;
    private JLabel lambdar;
    private JLabel hs;
    private JLabel noClientsInService;
    private JLabel noClientsOutService;
    private JLabel probabilityZeroClient;
    private JLabel probabilityOneClient;
    private JLabel probabilityTwoClients;
    private JLabel probabilityThreeClients;
    private JLabel probabilityFourClients;
    private JLabel probabilityFiveClient;

    private JLabel percentageServingClients;
    private JLabel percentageWithClientsAndOutOfService;

    private JLabel percentageServedClients;
    private JLabel percentageDesertion;
    private JLabel percentageDesertion1;
    private JLabel percentageDesertion2;

    private JLabel averageSystemLength;
    private JLabel averageQueueLength;
    private JLabel averageTimeInSystem;
    private JLabel averageTimeInQueue;
    private JLabel averageTimeInChannel;
    private JLabel averageTimeInterrupted;
    private JLabel wastedTime;
    private JLabel entering;
    private JLabel notEntering;
    private JLabel lambdaRaya;

    private ActionManager actionManager;

    public ResultsPanel(ActionManager actionManager) {
        super(new BorderLayout());
        this.actionManager = actionManager;
        this.setBorder(BorderFactory.createLineBorder(Color.decode("3107669"), 10));
        buildPanels();
    }

    private void buildPanels() {
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(15, 250));
        westPanel.setOpaque(false);

        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(15, 250));
        eastPanel.setOpaque(false);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(200, 15));
        northPanel.setOpaque(false);

        add(BorderLayout.NORTH, northPanel);
        add(BorderLayout.WEST, westPanel);
        add(BorderLayout.EAST, eastPanel);
        add(BorderLayout.CENTER, buildInfoPanel());
        add(BorderLayout.SOUTH, buildSouthPanel());
    }

    private JPanel buildSouthPanel() {
        JPanel south = new JPanel(new BorderLayout());
        south.setOpaque(false);

        JButton excelBt = new JButton(actionManager.getCreateExcelAction());
        excelBt.setText("Crear Excel");
        excelBt.setPreferredSize(new Dimension(120, 30));

        JButton newSimulationBt = new JButton(actionManager.getNewSimulationAction());
        newSimulationBt.setText("Nueva Simulación");
        newSimulationBt.setPreferredSize(new Dimension(120, 30));

        south.add(BorderLayout.EAST, excelBt);
        south.add(BorderLayout.WEST, newSimulationBt);

        return south;
    }

    private JPanel buildInfoPanel() {
        JPanel main = new JPanel(new BorderLayout());
        main.setOpaque(false);

        JPanel infoPanel = new JPanel(new GridLayout(12, 2, 7, 7));
        infoPanel.setPreferredSize(new Dimension(280, 400));
        infoPanel.setBorder(BorderFactory.createTitledBorder("RESULTADOS"));
        infoPanel.setOpaque(false);

        percentageInService = new JLabel("   F = ");
        percentageOutOfService = new JLabel("R = ");

        noClientsInService = new JLabel("   P(0,F) = ");
        noClientsOutService = new JLabel("P(0,R) = ");
        probabilityZeroClient = new JLabel(" p(0) =  ");
        probabilityOneClient = new JLabel("   p(1) = ");
        probabilityTwoClients = new JLabel("p(2) = ");
        probabilityThreeClients = new JLabel("   p(3) = ");
        probabilityFourClients = new JLabel("p(4) = ");
        probabilityFiveClient = new JLabel("   p(5) = ");

        percentageServingClients = new JLabel("   H = ");
        percentageWithClientsAndOutOfService = new JLabel("I = ");

        percentageServedClients = new JLabel("   P(atend) = ");
        percentageDesertion = new JLabel("P(aband) = ");
        percentageDesertion1 = new JLabel("");
        percentageDesertion2 = new JLabel("");

        averageSystemLength = new JLabel("   L = ");
        averageQueueLength = new JLabel("Lc = ");
        averageTimeInSystem = new JLabel("   W = ");
        averageTimeInQueue = new JLabel("Wc = ");
        averageTimeInChannel = new JLabel("   Tprom = ");
        averageTimeInterrupted = new JLabel("Wi = ");
        wastedTime = new JLabel("P(trabajo inutil) = ");
        notEntering = new JLabel("   P(No ingresos) = ");
        entering = new JLabel("   P(ingresos) = ");
        lambdaRaya = new JLabel("   LR = ");




        infoPanel.add(percentageInService);
        infoPanel.add(percentageOutOfService);
        infoPanel.add(noClientsInService);
        infoPanel.add(noClientsOutService);
        infoPanel.add(probabilityZeroClient);
        infoPanel.add(probabilityOneClient);
        infoPanel.add(probabilityTwoClients);
        infoPanel.add(probabilityThreeClients);
        infoPanel.add(probabilityFourClients);
        infoPanel.add(probabilityFiveClient);
        infoPanel.add(percentageServingClients);
        infoPanel.add(percentageWithClientsAndOutOfService);
        infoPanel.add(percentageServedClients);
        infoPanel.add(percentageDesertion);
//        infoPanel.add(percentageDesertion1);
//        infoPanel.add(percentageDesertion2);
        infoPanel.add(notEntering);
        infoPanel.add(entering);
        infoPanel.add(wastedTime);
        infoPanel.add(averageSystemLength);
        infoPanel.add(averageQueueLength);
        infoPanel.add(averageTimeInSystem);
        infoPanel.add(averageTimeInQueue);
        infoPanel.add(averageTimeInChannel);
        infoPanel.add(lambdaRaya);
        infoPanel.add(averageTimeInterrupted);



        JPanel dataPanel = new JPanel(new GridLayout(12, 2, 7, 7));
        dataPanel.setPreferredSize(new Dimension(280, 400));
        dataPanel.setBorder(BorderFactory.createTitledBorder("DATOS"));
        dataPanel.setOpaque(false);

        mu = new JLabel("MU = ");
        mur = new JLabel("   MU R = ");
        lambda = new JLabel("Lambda = ");
        lambdar = new JLabel("   Lambda R = ");
        hs = new JLabel("HS = ");

        dataPanel.add(mu);
        dataPanel.add(lambda);
        dataPanel.add(lambdar);
        dataPanel.add(mur);
        dataPanel.add(hs);



        main.add(BorderLayout.WEST, infoPanel);
        main.add(BorderLayout.EAST, dataPanel);
        return main;
    }


    public void setPercentageInService(double percentageInService) {
        this.percentageInService.setText("   F = " + percentageInService + " %");
    }

    public void setPercentageOutOfService(double percentageOutOfService) {
        this.percentageOutOfService.setText("R = " + percentageOutOfService + " %");
    }

    public void setNoClientsInService(double noClientsInService) {
        this.noClientsInService.setText("   P(0,F) = " + noClientsInService);
    }

    public void setNoClientsOutService(double noClientsOutService) {
        this.noClientsOutService.setText("P(0,R) = " + noClientsOutService);
    }

     public void setProbabilityZeroClient(double probabilityZeroClient) {
        this.probabilityZeroClient.setText("   p(0) = " + probabilityZeroClient);
    }

    public void setProbabilityOneClient(double probabilityOneClient) {
        this.probabilityOneClient.setText("p(1) = " + probabilityOneClient);
    }

    public void setProbabilityTwoClients(double probabilityTwoClients) {
        this.probabilityTwoClients.setText("   p(2) = " + probabilityTwoClients);
    }

    public void setProbabilityThreeClients(double probabilityThreeClients) {
        this.probabilityThreeClients.setText("p(3) = " + probabilityThreeClients);
    }

    public void setProbabilityFourClients(double probabilityFourClients) {
        this.probabilityFourClients.setText("   p(4) = " + probabilityFourClients);
    }

    public void setProbabilityFiveClients(double probabilityFiveClient) {
        this.probabilityFiveClient.setText("p(5) = " + probabilityFiveClient);
    }

    public void setPercentageServingClients(double percentageServingClients) {
        this.percentageServingClients.setText("   H = " + percentageServingClients + " %");
    }

    public void setPercentageWithClientsAndOutOfService(double percentageWithClientsAndOutOfService) {
        this.percentageWithClientsAndOutOfService.setText("I = " + percentageWithClientsAndOutOfService + " %");
    }

    public void setPercentageServedClients(double percentageServedClients) {
        this.percentageServedClients.setText("   P(at) =  " + percentageServedClients+ " %");
    }

    public void setPercentageDesertion(double percentageDesertion) {
        this.percentageDesertion.setText(" P(ab) = " + percentageDesertion+ " %");
    }

    public void setAverageSystemLength(double averageSystemLength) {
        this.averageSystemLength.setText("L = " + averageSystemLength);
    }

    public void setAverageQueueLength(double averageQueueLength) {
        this.averageQueueLength.setText("   Lc = " + averageQueueLength);
    }

    public void setAverageTimeInSystem(double averageTimeInSystem) {
        this.averageTimeInSystem.setText("W = " + averageTimeInSystem);
    }

    public void setAverageTimeInQueue(double averageTimeInQueue) {
        this.averageTimeInQueue.setText("   Wc = " + averageTimeInQueue);
    }

    public void setAverageTimeInChannel(double averageTimeInChannel) {
        this.averageTimeInChannel.setText("Tprom = " + averageTimeInChannel);
    }

    public void setPercentageDesertion1(double percentageDesertion1){
        this.percentageDesertion1.setText("   P(ab1) = " + percentageDesertion1 + "%");
    }

    public void setPercentageDesertion2(double percentageDesertion2){
        this.percentageDesertion2.setText("P(ab2) = " + percentageDesertion2+ "%");
    }

    public void setWastedTime(double wastedTime){
        this.wastedTime.setText("   P(ti) = " + wastedTime + "%");
    }

    public void setNotEntering(double notEntering){
        this.notEntering.setText("   P(ni) = " + notEntering + "%");
    }
    public void setLambdaRaya(double lambdaRaya){
        this.lambdaRaya.setText("   LR = " + lambdaRaya);
    }
    public void setEntering(double notEntering){
        this.entering.setText("P(i) = " + (100.0 - notEntering) + "%");
    }

    public void setAverageTimeInterrupted(double averageTimeInterrupted){
        this.averageTimeInterrupted.setText("Wi = " + averageTimeInterrupted);
    }

    public void setMu(double mu) {
        this.mu.setText("   MU = " + mu );
    }

    public void setMur(double mur) {
        this.mur.setText("   Lambda R = " + mur );
    }

    public void setLambda(double lambda) {
        this.lambda.setText("   Lambda = " + lambda );
    }

    public void setLambdar(double lambdar) {
        this.lambdar.setText("   MU R = " + lambdar );
    }

    public void setHs(double hs) {
        this.hs.setText("   HS = " + hs );
    }

}
