package control.actions;

import control.SimulationController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Laura
 * Date: 26/12/12
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class SimulateAction extends AbstractAction {
    private SimulationController simulationController;

    public SimulateAction(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            simulationController.submitData();
            simulationController.simulate();
        } catch (NumberFormatException ex) {
            System.out.println("number formal e");
        }

    }
}
