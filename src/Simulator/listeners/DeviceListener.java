package Simulator.listeners;

import Simulator.devices.Battery;
import Simulator.devices.Solarpanel;
import Simulator.devices.Abstract.ElectronicDevice;
/**
 * Interface for Event handling. this should be implemented in the mainclass
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public interface DeviceListener {
	/**
	 * add the Device into the Arraylist
	 * @param device object reference of the device which should be added
	 */
	public void addDevice(ElectronicDevice device);
	/**
	 * remove the Device from the Arraylist
	 * @param device object reference of the device which should be removed
	 */
	public void removeDevice(ElectronicDevice device);
	/**
	 * add the battery into the Arraylist
	 * @param battery object reference of the battery which should be added
	 */
	public void addBattery(Battery battery);
	/**
	 * remove the Battery from the Arraylist
	 * @param battery object reference of the battery which should be removed
	 */
	public void removeBattery(Battery battery);
	/**
	 * add the Solarpanel into the Arraylist
	 * @param solarpanel object reference of the Solarpanel which should be added
	 */
	public void addSolarpanel(Solarpanel solarpanel);
	/**
	 * remove the Solarpanel from the Arraylist
	 * @param solarpanel object reference of the Solarpanel which should be removed
	 */
	public void removeSolarpanel(Solarpanel solarpanel);
}
