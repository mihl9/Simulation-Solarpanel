package Simulator.devices;

import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;
/**
 * This Class represents a Radiator. It has the needed functions and properties of a Radiator
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class Radiator extends ElectronicDevice {
	/**
	 * Serial ID for GUI Objects
	 */
	private static final long serialVersionUID = 7825717296947636810L;
	/**
	 * Maximal Energy use. This Value is calculated via the Level
	 */
	private float maxWatt;
	/**
	 * The current temperatur
	 */
	private float mTemperature;
	/**
	 * Constructor of the Radiator
	 * @param listener needs the DeviceListener class for calling the events
	 * @param watt Value which defines the Max energy use
	 * @param level  the level of the Radiator
	 */
	public Radiator(DeviceListener listener, float watt, int level) {
		super(listener, 0, "img/radiator.png",true);
		this.maxWatt=watt;
		this.setHasSettingsPage(true);
		//this.setDeviceRunning(false);
		this.setLevel(level);
	}
	/**
	 *  get the current level 
	 * @return the current level
	 */
	public int getLevel() {
		return mLevel;
	}
	/**
	 * Set the level of the radiator
	 * @param level the radiator level
	 */
	public void setLevel(int level) {
		if(level>=this.MAX_LEVEL){
			this.mLevel = this.MAX_LEVEL;
		}else if(level<=0){
			this.mLevel = 0;
		}else{
			this.mLevel = level;
		}
		setEnergyUse();
	}
	/**
	 * Calculate the energy use based on the current energy level
	 */
	private void setEnergyUse(){
		this.setWattPower(this.maxWatt/MAX_LEVEL*this.mLevel);
	}
	/**
	 * Query the Current temperatur of the Radiator
	 * @return the current temperatur
	 */
	public float getTemperature() {
		return mTemperature;
	}
	/**
	 * set the temperature
	 * @param Temperature 
	 */
	@SuppressWarnings("unused")
	private void setTemperature(float Temperature) {
		this.mTemperature = Temperature;
		//TODO
	}
	
}
