package Simulator.devices;

import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;
/**
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 * This Class represents a Lamp. It has the needed functions and properties of a Lamp
 */
public class Lamp extends ElectronicDevice{

	public Lamp(DeviceListener listener, float watt) {
		super(listener, watt);
		// TODO Auto-generated constructor stub
	}

}
