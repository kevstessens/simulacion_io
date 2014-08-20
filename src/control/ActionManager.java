package control;

import control.actions.*;

public class ActionManager {

    private SimulateAction simulateAction;
    private CreateExcelAction createExcelAction;
    private NewSimulationAction newSimulationAction;
    private SelectFirstSimulation selectFirstSimulation;
    private SelectSecondSimulation selectSecondSimulation;
    private ExitAction exitAction;

    public void init(SimulationController simulationController) {
        simulateAction = new SimulateAction(simulationController);
        createExcelAction = new CreateExcelAction(simulationController);
        newSimulationAction = new NewSimulationAction(simulationController);
        selectFirstSimulation = new SelectFirstSimulation(simulationController);
        selectSecondSimulation = new SelectSecondSimulation(simulationController);
        exitAction= new ExitAction(simulationController);
    }

    public SimulateAction getSimulateAction() {
        return simulateAction;
    }

    public CreateExcelAction getCreateExcelAction() {
        return createExcelAction;
    }

    public NewSimulationAction getNewSimulationAction() {
        return newSimulationAction;
    }

    public SelectFirstSimulation getSelectFirstSimulation() {
        return selectFirstSimulation;
    }

    public SelectSecondSimulation getSelectSecondSimulation() {
        return selectSecondSimulation;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public void setExitAction(ExitAction exitAction) {
        this.exitAction = exitAction;
    }
}
