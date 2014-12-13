package Simulator.devices.Abstract;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Simulator.devices.interfaces.DeviceInterface;
import Simulator.listeners.DeviceListener;


/**
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 * Abstract class of an Electronic device. It inherits the basic functions and properties
 */
public class ElectronicDevice extends GuiPanelDevice implements ActionListener, DeviceInterface {
	/**
	 * Serial ID for GUI Objects
	 */
	private static final long serialVersionUID = -1857129969385914248L;
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
	 * Instance of the On/Off Button
	 */
	private JButton btnOnOff;
	/**
	 * Constructor of this class
	 * @param listener reference of the DeviceListener. for handling the Events
	 * @param watt Value which defines the Max energy use
	 */
	public ElectronicDevice(DeviceListener listener, float watt, String iconPath){
		super(iconPath);
		this.createdOnOffButton();
		if(listener!=null){
			this.listener = listener;
			this.setDeviceRunning(true);
			this.mID = ElectronicDevice.ID;
			ElectronicDevice.ID++;
			this.setWattPower(watt);
			listener.addDevice(this);
		}
	}
	
	protected void finalize () throws Throwable {
	    this.DisconnectFromEnergyHandler();
		super.finalize();
	}
	/**
	 * Remove the Electronic device from the Collection
	 */
	public void DisconnectFromEnergyHandler(){
		this.listener.removeDevice(this);
		ElectronicDevice.ID--;
	}
	
	/**
	 * Get the current Energy use of this object
	 * @return the energy use. (Watt)
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
		if(DeviceRunning){
			btnOnOff.setBackground(Color.green);
		}else{
			btnOnOff.setBackground(Color.red);
		}
	}

	
	/**
	 * get the ID of the Device
	 * @return the current ID
	 */
	public int getID() {
		return mID;
	}
	/**
	 * Create the On/Off button in the GUI object and sets the Action Listener
	 */
	private void createdOnOffButton(){
		this.setLayout(null);
		String btnText="";
		btnOnOff = new JButton(btnText);
		if(this.isDeviceRunning()){
			btnOnOff.setBackground(Color.green);
		}else{
			btnOnOff.setBackground(Color.red);
		}
		btnOnOff.setBounds(0, 0, 20, 20);
		this.add(btnOnOff);
		btnOnOff.addActionListener(this);
	}
	/**
	 * Action listener for the On Off button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setDeviceRunning(!this.isDeviceRunning());
	}
}
