package GUI.eventhandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.SimulationGUI;
import Simulator.EnergyHandler;
import Simulator.tools.Joule;
import Simulator.tools.Joule.TimeUnit;
/**
 * This Class is the Eventhandler for the timer in the Simulator GUI.
 * @created 13.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class TimerHandler implements ActionListener {
	/**
	 * this value saves the instance of the GUI
	 */
	private SimulationGUI mfrmSimulation;
	/**
	 * this value saves the instance of the Energy Handler
	 */
	private EnergyHandler mEnergyHandler;
	/**
	 * This var saves the time. How long the simulation already is running
	 */
	private float mTimeRunning=0;
	/**
	 * Constructor of this class.
	 * @param myGui  the Instance of the GUI
	 * @param handler the Instance of the current Energy handler
	 */
	public TimerHandler(SimulationGUI myGui, EnergyHandler handler){
		this.mfrmSimulation = myGui;
		this.mEnergyHandler = handler;
	}
	/**
	 * this event is called everytime when the timer of the GUI timer ticks. it pushes the Simulation and refreshes the data 
	 * @param e The Information object about the Action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.mEnergyHandler!=null){
			//recalculate the energy use
			this.mEnergyHandler.CheckEnergy();
			if(this.mfrmSimulation.getTxtRuntime().getText().equals(""+this.mTimeRunning)){
				
			}else{
				this.mTimeRunning = 0;
			}
			this.mTimeRunning++;
			//load the Information into the boxes
			this.mfrmSimulation.getTxtTotalGenerated().setText(""+this.mEnergyHandler.getTotalGeneratedEnergy());
			this.mfrmSimulation.getTxtCurrentOutput().setText(""+this.mEnergyHandler.getCurrentSolarpanelsEnergyOutput());
			this.mfrmSimulation.getTxtCurrentUse().setText(""+this.mEnergyHandler.getCurrentEnergyUse());
			this.mfrmSimulation.getTxtRuntime().setText(""+this.mTimeRunning);
			this.mfrmSimulation.getTxtCurrBatteryCharge().setText(""+Joule.convertTimeUnit(this.mEnergyHandler.getBatteryEnergyReservoir(), TimeUnit.s, TimeUnit.h));
			//print the information
			System.out.println(""+this.mEnergyHandler.getTotalGeneratedEnergy()+" Watt");
			System.out.println(""+this.mEnergyHandler.getCurrentSolarpanelsEnergyOutput() + "  Watt/Sekunden");
			System.out.println(""+this.mEnergyHandler.getCurrentEnergyUse()+"Watt/Sekunden");
			System.out.println(this.mTimeRunning+" Sekunden");
			
			//this.mfrmSimulation.repaint();
		}
		
	}

}
