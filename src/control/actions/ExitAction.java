package control.actions;

import control.SimulationController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitAction extends AbstractAction {
    private final SimulationController simulationController;

    public ExitAction(SimulationController simulationController) {
        this.simulationController = simulationController;

    }


    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Gracias por utilizar nuestro sistema de simulación", "Salir", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
}
