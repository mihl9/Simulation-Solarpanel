package Simulator.devices.Abstract;

import Simulator.listeners.DeviceListener;

/**
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 * Abstract class of an Electronic device. It inherits the basic functions and properties
 */
public class ElectronicDevice {
	/**
	 * Index of the object Battery. It counts up everytime a object is created
	 */
	private static int ID = 0;
	/**
	 * Saves the ID of this object
	 */
	private int mID;
	/**
	 * Inherits the value of the current Power which is used from the device
	 */
	private float mWattPower=50;
	/**
	 * On/Off switch of the Device
	 */
	private boolean mDeviceRunning=true;
	/**
	 * listener class. For calling the EVents
	 */
	private DeviceListener listener;
	/**
	 * Constructor of this class
	 * @param listener reference of the DeviceListener. for handling the Events
	 * @param watt Value which defines the Max energy use
	 */
	public ElectronicDevice(DeviceListener listener, float watt){
		this.listener = listener;
		this.setDeviceRunning(false);
		this.mID = ElectronicDevice.ID;
		ElectronicDevice.ID++;
		this.setWattPower(watt);
		listener.addDevice(this);
	}
	/**
	 * Remove the Electronic device from the Collection
	 */
	public void DisconnectFromEnergyHandler(){
		this.listener.removeDevice(this);
	}
	
	/**
	 * Get the current Energy use of this object
	 * @return the energy use.
	 */
	public float getWattPower() {
		if(this.isDeviceRunning()){
			return mWattPower;
		}else{
			return 0;
		}
	}
	
	/**
	 * Set the energy use of this object
	 * @param wattPower the new energy use
	 */
	protected void setWattPower(float wattPower) {
		if(wattPower>0){
			this.mWattPower = wattPower;
		}else{
			//make no changes
		}
		
	}
	/**
	 * check if the Device is running
	 * @return the boolean value if the device is running
	 */
	public boolean isDeviceRunning() {
		return mDeviceRunning;
	}
	/**
	 * set the Device status
	 * @param DeviceRunning the value which defines if the device is running or not
	 */
	public void setDeviceRunning(boolean DeviceRunning) {
		this.mDeviceRunning = DeviceRunning;
	}

	/**
	 * get the ID of the Device
	 * @return the current ID
	 */
	public int getID() {
		return mID;
	}
}
