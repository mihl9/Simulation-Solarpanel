package Simulator.devices.Abstract;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Simulator.listeners.DeviceListener;

/**
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 * Abstract class of an Electronic device. It inherits the basic functions and properties
 */
public class ElectronicDevice extends JLabel {
	/**
	 * Serial ID for GUI Objects
	 */
	private static final long serialVersionUID = -1857129969385914248L;
	/**
	 * 
	 */
	private ImageIcon mImage;
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
	public ElectronicDevice(DeviceListener listener, float watt, String iconPath){
		if(listener!=null){
			this.listener = listener;
			this.setDeviceRunning(true);
			this.mID = ElectronicDevice.ID;
			ElectronicDevice.ID++;
			this.setWattPower(watt);
			listener.addDevice(this);
			this.setImage(iconPath);
		}
	}
	/**
	 * Remove the Electronic device from the Collection
	 */
	public void DisconnectFromEnergyHandler(){
		this.listener.removeDevice(this);
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
	}

	/**
	 * 
	 * @return
	 */
	public String getImage(){
		return mImage.toString();
	}
	/**
	 * 
	 * @param sPath
	 */
	private void setImage(String sPath){
		File oFile = new File(sPath);
		if(oFile.exists()){
			mImage = ScaleImage(new ImageIcon(sPath), getSize().width, getSize().height) ;
			setIcon(mImage);
		}
		oFile = null;
	}
	
	private ImageIcon ScaleImage(ImageIcon oImage, Integer iNewWidth, Integer iNewHeight){
		//Load the Image into the Buffer and resize the whole Image
		Image img = oImage.getImage();
		BufferedImage bi = new BufferedImage(iNewWidth, iNewHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0,0, iNewWidth, iNewHeight, null);
		//Load the resized Image into the Icon object
		oImage = new ImageIcon(bi);
		return oImage;
	}
	/**
	 * get the ID of the Device
	 * @return the current ID
	 */
	public int getID() {
		return mID;
	}
}
