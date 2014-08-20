package model;

import control.SimulationController;

public class Simulator {

    public void init(){
        SimulationController  simulationController = new SimulationController();
        simulationController.init();
    }
}
