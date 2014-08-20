package control.actions;

import control.SimulationController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Laura
 * Date: 01/01/13
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public class NewSimulationAction extends AbstractAction{
    private SimulationController simulationController;

    public NewSimulationAction(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    public void actionPerformed(ActionEvent e) {
        simulationController.startNewSimulation();
    }
}
