package Simulator.devices;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JProgressBar;

import Simulator.devices.interfaces.DeviceInterface;
import Simulator.listeners.DeviceListener;
import Simulator.tools.Joule;
import Simulator.tools.Joule.TimeUnit;
/**
 * This Class represents a battery. It has the needed functions and properties of a battery
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class Battery extends JProgressBar implements DeviceInterface{
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
	private final int MAX_CAPACITY_AMPERE_SECOND = 64800;
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
	/***
	 * Boolean value which indicates if the remaining time should be displayed
	 */
	private boolean mTimeRemainingVisible=false;
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
		super(0,100);
		if(listener!=null){
			this.listener = listener;
			this.mID = Battery.ID;
			Battery.ID++;
			super.setMaximum(100);
			this.setValue(0);
			this.setStringPainted(true);
			this.setAmperePercent(energyInPercent);
			this.setTimeRemainingVisible(true);
			this.listener.addBattery(this);
			this.setVisible(true);
			
		}
	}
	/**
	 * Remove the Battery from the Collection
	 */
	public void DisconnectFromEnergyHandler(){
		this.listener.removeBattery(this);
		Battery.ID--;
	}
	/**
	 * Adds the given Amount of ampere to the current battery level. If the value is negative then the dischargeBattery method is called
	 * @param amper the amount which should be added (Ampere/Seconds)
	 */
	public void chargeBattery(float ampere){
		float temp;
		if(ampere<0){
			dischargeBattery(-1*ampere);
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
	/**
	 * Subtracts the given Amount of ampere to the current battery level. If the value is negative then the chargeBattery method is called
	 * @param amper the amount which should be subtracted (Ampere/Seconds)
	 */
	public void dischargeBattery(float ampere){
		float temp;
		if(ampere<0){
			chargeBattery(-1*ampere);
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
			this.setValue((int)(100.0/MAX_CAPACITY_AMPERE_SECOND*ampere));
			//this.setString("Hallodu");
			this.repaint();
		}
	}
	/**
	 * Set the Energy Level based on the percent
	 * @param percent the percent of the Energy Level
	 */
	public boolean setAmperePercent(double percent){
		 float result=0;
		 boolean success=true;
		 if(percent<=100 && percent>=0){
			try {
				result = (float)(MAX_CAPACITY_AMPERE_SECOND/100*percent);
				this.setAmpereSecond(result);
			} catch (Exception e) {
				// if an error occurs the set the Battery level to 100%
				//result = MAX_CAPACITY_AMPERE_SECOND;
				success=false;
			}
		 }else{
			 success=false;
		 }
		return success;
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
			result = (int)100.0/MAX_CAPACITY_AMPERE_SECOND*this.getAmpere(TimeUnit.s);
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
		float AmperePerSecond;
		float result;
		try {
			AmperePerSecond = this.mChargAmpereSecond - this.mDischargeAmpereSecond;
			if(AmperePerSecond>=0){
				result = (this.MAX_CAPACITY_AMPERE_SECOND-this.mAmpereSecond)/AmperePerSecond;
			}else{
				result = this.mAmpereSecond/(-1*AmperePerSecond);
			}
		} catch (Exception e) {
			result = 0;
		}
		
		return result;
	}
	/**
	 * Indicates if the Remaining time shoudl be displayed or not
	 * @param visibleValue if the remaining time should be displayed
	 */
	public void setTimeRemainingVisible(boolean visible){
		mTimeRemainingVisible = visible;
	}
	/**
	 * Get the value if the remaining time should be displayed
	 * @return the boolean if the time should be displayed
	 */
	public boolean getTimeRemainingVisible(){
		return this.mTimeRemainingVisible;
	}
	/**
	 * This method is for drawing purpose, and its used for drawing the Time remaining string into the progressbar
	 * @param g the graphics element which should be drawn
	 */
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth() - 1;
        int h = getHeight() - 1;
        Graphics2D g2d = (Graphics2D) g;
		if(getTimeRemainingVisible()){
			FontMetrics fm = g.getFontMetrics();
            int stringW = 0;
            int stringH = 0;
            g2d.setColor(getForeground());
            if (isEnabled()) {
            	String timeRemaining="";
            	NumberFormat numberFormat = new DecimalFormat("0.0");
                numberFormat.setRoundingMode(RoundingMode.DOWN);
            	try {
            		timeRemaining = ""+numberFormat.format(Joule.convertTimeUnit(this.getRemainingTime(), TimeUnit.s, TimeUnit.m)) +" Min";
            		float AmperePerSecond = this.mChargAmpereSecond - this.mDischargeAmpereSecond;
                	if(AmperePerSecond>=0){
        				g2d.setColor(Color.green);
        			}else{
        				g2d.setColor(Color.red);
        			}
				} catch (Exception e) {
					// TODO: handle exception
					g2d.setColor(Color.black);
				}
            	
            	stringW = fm.stringWidth(timeRemaining);
                stringH = ((h - fm.getHeight()) / 2) + fm.getAscent()+13;
                g2d.drawString(timeRemaining, (w-stringW)-stringW/2, stringH);
            }
		}
	}
	/**
	 * Gets the ID of the Current Object
	 * @return The ID of the current object
	 */
	public int getID(){
		return this.mID;
	}
}
