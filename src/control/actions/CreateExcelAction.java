package control.actions;

import control.SimulationController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Laura
 * Date: 01/01/13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class CreateExcelAction extends AbstractAction{
    SimulationController simulationController;

    public CreateExcelAction(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    public void actionPerformed(ActionEvent e) {
        simulationController.createExcel();
    }
}
