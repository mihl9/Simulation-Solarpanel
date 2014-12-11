package Simulator.devices;

import Simulator.Weather;
import Simulator.listeners.DeviceListener;
/**
 * @created 11.12.2014
 * @author Michael Huber
 * @version 1.0
 * This Class represents a Solarpanel. It has the needed functions and properties of a Solarpanel
 */
public class Solarpanel {
	/**
	 * Index of the object Battery. It counts up everytime a object is created
	 */
	private static int ID = 0;
	/**
	 * Saves the ID of this object
	 */
	private int mID;
	/**
	 * listener class. For calling the EVents
	 */
	private DeviceListener listener;
	/**
	 * the instance of the current weather
	 */
	private Weather mWeather;
	/**
	 * the Max amount of the energy production(Watt Peak)
	 */
	private float mMaxEnergyProduction;
	/**
	 * Constructor of the current class
	 * @param listener needs the DeviceListener class for calling the events
	 * @param MaxEnergy the Watt Peak of the Solarpanel
	 */
	public Solarpanel(DeviceListener listener, float MaxEnergy){
		if(listener!=null){
			this.listener = listener;
			this.mID = Solarpanel.ID;
			Solarpanel.ID++;
			this.mMaxEnergyProduction = MaxEnergy;
			this.mWeather = Weather.getInstance();
			//register this object in the EnergyHandler
			this.listener.addSolarpanel(this);
		}
	}
	/**
	 * calculates the current energy output
	 * @return the current energy output
	 */
	public float getEnergyOutput(){
		float result;
		switch (this.mWeather.getCurrentWeather()) {
			case nice:
				//full power
				result = this.mMaxEnergyProduction;
				break;
			case cloudy:
				//when if it is cloudy the only the half of the power is produced
				result = this.mMaxEnergyProduction/2;
				break;
			case rainy:
				result = this.mMaxEnergyProduction/4;
				break;
			case night:
				//in the night no energy can be produced
				result = 0;
				break;
			default:
				//unknown selection return 0
				result = 0;
				break;
		}
		return result;
	}
	
	/**
	 * Gets the ID of the Current Object
	 * @return The ID of the current object
	 */
	public int getID(){
		return this.mID;
	}
}
