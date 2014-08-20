package view;

import control.ActionManager;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    private ActionManager actionManager;
    private JTextField clientsPerHour;
    private JTextField attentionSpeed;
    private JTextField interruptionPerHour;
    private JTextField interruptionFrequency;
    private JTextField duration;

    public DataPanel(ActionManager actionManager) {
        super(new BorderLayout());
        this.actionManager = actionManager;
        this.setBorder(BorderFactory.createLineBorder(Color.decode("3107669"), 10));
        this.setPreferredSize(new Dimension(300,300));
        addPanels();
    }

    private void addPanels() {
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(15, 250));
        westPanel.setOpaque(false);

        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(15, 250));
        eastPanel.setOpaque(false);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(200, 15));
        northPanel.setOpaque(false);

        add(BorderLayout.CENTER, buildFieldsPanel());
        add(BorderLayout.SOUTH, buildSouthPanel());
        add(BorderLayout.WEST, westPanel);
        add(BorderLayout.EAST, eastPanel);
        add(BorderLayout.NORTH, northPanel);
    }

    private JPanel buildFieldsPanel() {
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 1));
        fieldsPanel.setPreferredSize(new Dimension(170, 200));
        fieldsPanel.setBorder(BorderFactory.createTitledBorder("DATOS"));
        fieldsPanel.add(buildClientsPanel());
        fieldsPanel.add(buildAttentionPanel());
        fieldsPanel.add(buildInterruptionPanel());
        fieldsPanel.add(buildInterruptionFqPanel());
        fieldsPanel.add(buildDurationPanel());

        return fieldsPanel;
    }

    private JPanel buildSouthPanel() {
        JButton submitBt = new JButton(actionManager.getSimulateAction());
        submitBt.setPreferredSize(new Dimension(100, 30));
        submitBt.setText("OK");

        JPanel southPanel = new JPanel();
        southPanel.add(submitBt);

        return southPanel;
    }

    private JPanel buildDurationPanel() {
        JPanel durationPanel = new JPanel();
        JLabel durationLabel = new JLabel("HS");
        durationLabel.setPreferredSize(new Dimension(50, 30));
        duration = new JTextField();
        duration.setPreferredSize(new Dimension(100, 30));

        durationPanel.add(durationLabel);
        durationPanel.add(duration);
        return durationPanel;
    }

    private JPanel buildInterruptionFqPanel() {
        JPanel interruptionFrequencyPanel = new JPanel();
        JLabel interruptionFrequencyLabel = new JLabel("Mu r");
        interruptionFrequencyLabel.setPreferredSize(new Dimension(50, 30));
        interruptionFrequency = new JTextField();
        interruptionFrequency.setPreferredSize(new Dimension(100, 30));

        interruptionFrequencyPanel.add(interruptionFrequencyLabel);
        interruptionFrequencyPanel.add(interruptionFrequency);
        return interruptionFrequencyPanel;
    }

    private JPanel buildInterruptionPanel() {
        JPanel interruptionPanel = new JPanel();
        JLabel interruptionPerHourLabel = new JLabel("Lambda r");
        interruptionPerHourLabel.setPreferredSize(new Dimension(50, 30));
        interruptionPerHour = new JTextField();
        interruptionPerHour.setPreferredSize(new Dimension(100, 30));

        interruptionPanel.add(interruptionPerHourLabel);
        interruptionPanel.add(interruptionPerHour);
        return interruptionPanel;
    }

    private JPanel buildAttentionPanel() {
        JPanel attentionPanel = new JPanel();
        JLabel attentionSpeedLabel = new JLabel("Mu");
        attentionSpeedLabel.setPreferredSize(new Dimension(50, 30));
        attentionSpeed = new JTextField();
        attentionSpeed.setPreferredSize(new Dimension(100, 30));

        attentionPanel.add(attentionSpeedLabel);
        attentionPanel.add(attentionSpeed);
        return attentionPanel;
    }

    private JPanel buildClientsPanel() {
        JPanel clientsPanel = new JPanel();
        JLabel clientsPerHourLabel = new JLabel("Lambda");
        clientsPerHourLabel.setPreferredSize(new Dimension(50, 30));
        clientsPerHour = new JTextField();
        clientsPerHour.setPreferredSize(new Dimension(100, 30));

        clientsPanel.add(clientsPerHourLabel);
        clientsPanel.add(clientsPerHour);
        return clientsPanel;
    }

    public float getClientsPerHour() {
        return Float.parseFloat(clientsPerHour.getText());
    }

    public float getAttentionSpeed() {
        return Float.parseFloat(attentionSpeed.getText());
    }

    public float getInterruptionPerHour() {
        return Float.parseFloat(interruptionPerHour.getText());
    }

    public float getInterruptionFrequency() {
        return Float.parseFloat(interruptionFrequency.getText());
    }

    public double getDuration() {
        return Float.parseFloat(duration.getText());
    }

    public void clear() {
        clientsPerHour.setText("");
        attentionSpeed.setText("");
        interruptionFrequency.setText("");
        interruptionPerHour.setText("");
        duration.setText("");
    }
}
