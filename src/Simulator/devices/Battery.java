package Simulator.devices;

import javax.swing.JProgressBar;

import Simulator.listeners.DeviceListener;
import Simulator.tools.Joule;
import Simulator.tools.Joule.TimeUnit;
/**
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 * This Class represents a battery. It has the needed functions and properties of a battery
 */
public class Battery extends JProgressBar{
	/**
	 * Serial ID for GUI Objects
	 */
	private static final long serialVersionUID = -8207266535421556855L;
	/**
	 * Index of the object Battery. It counts up everytime a object is created
	 */
	private static int ID = 0;
	/**
	 * Defines the Max Capacity of the Battery
	 */
	private final int MAX_CAPACITY_AMPERE_SECOND = 61200;
	/**
	 * inherits the capacity of the battery
	 */
	private float mAmpereSecond;
	/**
	 * inherits the AMount of Ampere/Seconds which is added to the battery
	 */
	private float mChargAmpereSecond;
	/**
	 * inherits the AMount of Ampere/Seconds which is removed to the battery
	 */
	private float mDischargeAmpereSecond;
	/**
	 * Saves the ID of this object
	 */
	private int mID;
	/**
	 * listener class. For calling the EVents
	 */
	private DeviceListener listener;
	/**
	 * The Constructor of the Battery class.
	 * @param energyInPercent
	 * @param listener needs the DeviceListener class for calling the events
	 */
	public Battery(DeviceListener listener, double energyInPercent){
		if(listener!=null){
			this.listener = listener;
			this.mID = Battery.ID;
			Battery.ID++;
			this.setAmperePercent(energyInPercent);
			this.listener.addBattery(this);
		}
	}
	/**
	 * Remove the Battery from the Collection
	 */
	public void DisconnectFromEnergyHandler(){
		this.listener.removeBattery(this);
	}
	/**
	 * Adds the given Amount of ampere to the current battery level
	 * @param amper the amount which should be added (Ampere/Seconds)
	 */
	public void chargeBattery(float ampere){
		float temp;
		if(ampere<0){
			return;
		}
		this.mChargAmpereSecond = ampere; 
		temp = this.getAmpere(TimeUnit.s)+ampere;
		if(temp<=0){
			//the ampere value has reached the 0 point
			this.setAmpereSecond(0);
		}else if(temp>=MAX_CAPACITY_AMPERE_SECOND){
			//The ampere Value has reached the max capacity
			this.setAmpereSecond(MAX_CAPACITY_AMPERE_SECOND);
		}else{
			//all is fine set the value
			this.setAmpereSecond(temp);
		}
	}
	public void dischargeBattery(float ampere){
		float temp;
		if(ampere<0){
			return;
		}
		this.mDischargeAmpereSecond = ampere;
		temp = this.getAmpere(TimeUnit.s)-ampere;
		if(temp<=0){
			//the ampere value has reached the 0 point
			this.setAmpereSecond(0);
		}else if(temp>=MAX_CAPACITY_AMPERE_SECOND){
			//The ampere Value has reached the max capacity
			this.setAmpereSecond(MAX_CAPACITY_AMPERE_SECOND);
		}else{
			//all is fine set the value
			this.setAmpereSecond(temp);
		}
	}
	/**
	 * Replace the Current energy level with the given value
	 * @param amper the value which should be set as energy level
	 */
	public void setAmpereSecond(float ampere){
		if(ampere>=0 && ampere<=MAX_CAPACITY_AMPERE_SECOND){
			this.mAmpereSecond = ampere;
			this.setValue((int)(100/MAX_CAPACITY_AMPERE_SECOND*ampere));
		}
	}
	/**
	 * Set the Energy Level based on the percent
	 * @param percent the percent of the Energy Level
	 */
	public void setAmperePercent(double percent){
		 float result;
		try {
			result = (float)(MAX_CAPACITY_AMPERE_SECOND/100*percent);
		} catch (Exception e) {
			// if an error occurs the set the Battery level to 100%
			result = MAX_CAPACITY_AMPERE_SECOND;
		}
		this.setAmpereSecond(result);
	}
	/**
	 * Get the Ampere in the Given time unit
	 * @param unit the Time unit which should be returned
	 * @return the ampere value in the Given time unit
	 */
	public float getAmpere(TimeUnit unit){
		return Joule.convertTimeUnit(this.mAmpereSecond, TimeUnit.s, unit);
	}
	/**
	 * Calculate the current percentage of the battery capacity and return it
	 * @return the Current capacity percentage
	 */
	public double getEnergyPercent(){
		float result;
		try {
			result = 100/MAX_CAPACITY_AMPERE_SECOND*this.getAmpere(TimeUnit.s);
		} catch (Exception e) {
			// TODO: handle exception
			result = 0;
		}
		return result;
	}
	/**
	 * get the Amount of seconds which is need until the Battery is charged or uncharged based on the Energy consume
	 * @return the amount of Seconds which is needed
	 */
	public float getRemainingTime(){
		float wattPerSecond;
		float result;
		try {
			wattPerSecond = this.mChargAmpereSecond - this.mDischargeAmpereSecond;
			if(wattPerSecond>=0){
				result = (this.MAX_CAPACITY_AMPERE_SECOND-this.mAmpereSecond)/wattPerSecond;
			}else{
				result = this.mAmpereSecond/(-1*wattPerSecond);
			}
		} catch (Exception e) {
			result = 0;
		}
		
		return result;
	}
	
	/**
	 * Gets the ID of the Current Object
	 * @return The ID of the current object
	 */
	public int getID(){
		return this.mID;
	}
}
