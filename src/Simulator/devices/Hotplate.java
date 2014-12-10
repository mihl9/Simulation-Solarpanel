package Simulator.devices;

import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;
/**
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 * This Class represents a Hotplate. It has the needed functions and properties of a Hotplate
 */
public class Hotplate extends ElectronicDevice {
	/**
	 * The Max level of the Hotplate
	 */
	private final int MAX_LEVEL=3;
	/**
	 * Current Level
	 */
	private int mLevel=0;
	/**
	 * Maximal Energy use. Which can be set via the level
	 */
	private float maxWatt;
	/**
	 * The current Temperatur
	 */
	private float mTemperature;
	/**
	 * Constructor of the Hotplate
	 * @param listener needs the DeviceListener class for calling the events
	 * @param watt Value which defines the Max energy use
	 * @param level  the level of the hotplate
	 */
	public Hotplate(DeviceListener listener, float watt, int level) {
		super(listener, 0);
		this.maxWatt=watt;
		this.setDeviceRunning(false);
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
	 * Set the level of the hotplate
	 * @param level the hotplate level
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
	 * Query the Current temperatur of the hotplate
	 * @return the current temperatur
	 */
	public float getTemperature() {
		return mTemperature;
	}
	/**
	 * set the temperature
	 * @param Temperature 
	 */
	private void setTemperature(float Temperature) {
		this.mTemperature = mTemperature;
		//TODO
	}
	

}
