package Simulator.devices;

import Simulator.listeners.DeviceListener;
import Simulator.tools.Joule;
import Simulator.tools.Joule.TimeUnit;
/**
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 * This Class represents a battery. It has the needed functions and properties of a battery
 */
public class Battery{
	/**
	 * Index of the object Battery. It counts up everytime a object is created
	 */
	private static int ID = 0;
	/**
	 * Defines the Max Capacity of the Battery
	 */
	final int MAX_CAPACITY_AMPER_SECOND = 61200;
	/**
	 * inherits the capacity of the battery
	 */
	private float mAmperSecond;
	/**
	 * Saves the ID of this object
	 */
	private int mID;
	/**
	 * listener class. For calling the EVents
	 */
	private DeviceListener listener;
	/**
	 * The Constructor of the Battery class.
	 * @param energyInPercent
	 * @param listener needs the DeviceListener class for calling the events
	 */
	public Battery(double energyInPercent, DeviceListener listener){
		if(listener==null){
			this.listener = listener;
			this.mID = Battery.ID;
			Battery.ID++;
			this.setAmperPercent(energyInPercent);
			this.listener.addBattery(this);
		}
	}
	/**
	 * Remove the Battery from the Collection
	 */
	public void DisconnectFromEnergyHandler(){
		this.listener.removeBattery(this);
	}
	/**
	 * 
	 * @param amper
	 */
	public void addAmperSecond(float amper){
		float temp;
		temp = this.getAmper(TimeUnit.s)+amper;
		if(temp<=0){
			//the ampere value has reached the 0 point
			this.setAmperSecond(0);
		}else if(temp>=MAX_CAPACITY_AMPER_SECOND){
			//The ampere Value has reached the max capacity
			this.setAmperSecond(MAX_CAPACITY_AMPER_SECOND);
		}else{
			//all is fine set the value
			this.setAmperSecond(temp);
		}
	}
	/**
	 * Replace the Current energy level with the given value
	 * @param amper the value which should be set as energy level
	 */
	public void setAmperSecond(float amper){
		if(amper>=0 && amper<=MAX_CAPACITY_AMPER_SECOND){
			this.mAmperSecond = amper;
		}
	}
	/**
	 * Set the Energy Level based on the percent
	 * @param percent the percent of the Energy Level
	 */
	public void setAmperPercent(double percent){
		 float result;
		try {
			result = (float)(MAX_CAPACITY_AMPER_SECOND/100*percent);
		} catch (Exception e) {
			// if an error occurs the set the Battery level to 100%
			result = MAX_CAPACITY_AMPER_SECOND;
		}
		this.setAmperSecond(result);
	}
	/**
	 * Get the Ampere in the Given
	 * @param unit the Time unit which should be returned
	 * @return the ampere value in the Given time unit
	 */
	public float getAmper(TimeUnit unit){
		return Joule.convertTimeUnit(this.mAmperSecond, TimeUnit.s, unit);
	}
	/**
	 * 
	 * @return
	 */
	public double getEnergyPercent(){
		float result;
		try {
			result = 100/MAX_CAPACITY_AMPER_SECOND*this.getAmper(TimeUnit.s);
		} catch (Exception e) {
			// TODO: handle exception
			result = 0;
		}
		return result;
	}
	
	public float getRemainingTime(){
		//TODO Implement
		return 0;
	}
	/**
	 * Gets the ID of the Current Object
	 * @return The ID of the current object
	 */
	public int getID(){
		return this.mID;
	}
}
