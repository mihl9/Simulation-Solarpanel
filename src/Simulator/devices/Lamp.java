package Simulator.devices;

import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;
/**
 * This Class represents a Lamp. It has the needed functions and properties of a Lamp
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class Lamp extends ElectronicDevice{
	/**
	 * Serial ID for GUI Objects
	 */
	private static final long serialVersionUID = 8614009551610696342L;
	/**
	 * Constructor of the Lamp class
	 * @param needs the DeviceListener class for calling the events
	 * @param watt Value which defines the Max energy use
	 */
	public Lamp(DeviceListener listener, float watt) {
		super(listener, watt,"img/lamp.png");
	}
	/**
	 * no Action should be taken by this method
	 * return Always 0
	 */
	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * No Action should be taken
	 * @param level of the device
	 */
	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub
		
	}

}
