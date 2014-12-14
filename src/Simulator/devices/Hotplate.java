package Simulator.devices;

import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;
/**
 * This Class represents a Hotplate. It has the needed functions and properties of a hot plate
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class Hotplate extends ElectronicDevice {
	/**
	 * Serial ID for GUI Objects
	 */
	private static final long serialVersionUID = -133772669964567629L;
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
		super(listener, 0, "img/hotplate.png",true);
		this.maxWatt=watt;
		//this.setDeviceRunning(true);
		this.setLevel(level);
	}
	/**
	 * get the current level 
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
	@SuppressWarnings("unused")
	private void setTemperature(float Temperature) {
		this.mTemperature = Temperature;
		//TODO
	}
	

}
