package control.actions;

import control.SimulationController;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class SelectSecondSimulation extends AbstractAction{
    private final SimulationController simulationController;
    public SelectSecondSimulation(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    public void actionPerformed(ActionEvent e) {
        simulationController.selectSecondSimulation();
    }
}
