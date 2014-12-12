package Simulator;

import java.util.Timer;

import Simulator.Weather.weatherTyp;
import Simulator.devices.Battery;
import Simulator.devices.Hotplate;
import Simulator.devices.Lamp;
import Simulator.devices.Solarpanel;
import Simulator.tools.Joule;

public class SimulationHouse {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimulationHouse simulation = new SimulationHouse();
		
	}
	public static Timer mTimer;
	public EnergyHandler mEnergyHandler;
	public Battery bat;
	public SimulationHouse(){
		int i=0;
		mEnergyHandler = new EnergyHandler();
		new Solarpanel(this.mEnergyHandler, 30000);
		new Solarpanel(this.mEnergyHandler, 20);
		new Solarpanel(this.mEnergyHandler, 20);
		new Solarpanel(this.mEnergyHandler, 20);
		new Solarpanel(this.mEnergyHandler, 20);
		new Solarpanel(this.mEnergyHandler, 20);
		new Lamp(this.mEnergyHandler, 20);
		new Hotplate(this.mEnergyHandler, 50, 3);
		bat = new Battery(this.mEnergyHandler,50);
		Weather.getInstance().setCurrentWeather(weatherTyp.nice);
		while(i<14){
			mEnergyHandler.CheckEnergy();
			System.out.println("Energiezufuhr: " + this.mEnergyHandler.getCurrentSolarpanelsEnergyOutput());
			System.out.println("Energieverbarauch: " + this.mEnergyHandler.getCurrentEnergyUse());
			System.out.println("Batteriezustand(Amps): " + this.mEnergyHandler.getBatteryEnergyReservoir());
			System.out.println("Batteriezustand(Watt): " + Joule.calcPerformance(12, this.mEnergyHandler.getBatteryEnergyReservoir()) );
			System.out.println("Watt Per Second: " + this.mEnergyHandler.getWattPerSecond());
			System.out.println("Seconds Until battery has reached limit: " + bat.getRemainingTime() + " Seconds");
			i++;
		}
	}
	
	public void start(){
		// TODO
	}
	
	public void stop(){
		// TODO
	}
}
