package control.actions;

import control.SimulationController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SelectFirstSimulation extends AbstractAction{

    private final SimulationController simulationController;

    public SelectFirstSimulation(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    public void actionPerformed(ActionEvent e) {
       simulationController.selectFirstSimulation();
    }
}
