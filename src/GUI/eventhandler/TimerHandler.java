package GUI.eventhandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.SimulationGUI;
import Simulator.EnergyHandler;

public class TimerHandler implements ActionListener {

	private SimulationGUI mfrmSimulation;
	private EnergyHandler mEnergyHandler;
	
	public TimerHandler(SimulationGUI myGui, EnergyHandler handler){
		this.mfrmSimulation = myGui;
		this.mEnergyHandler = handler;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.mEnergyHandler!=null){
			this.mEnergyHandler.CheckEnergy();
			//this.mfrmSimulation.repaint();
		}
		
	}

}
