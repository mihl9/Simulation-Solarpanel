package Simulator.devices.interfaces;
/**
 * the interface for all devices. it inherits the Disconnect method
 * @created 14.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public interface DeviceInterface {
	/**
	 * Remove the device from the Collection in the Energy Handler
	 */
	public void DisconnectFromEnergyHandler();
}
