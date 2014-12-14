package Simulator.devices;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import Simulator.Weather;
import Simulator.devices.Abstract.GuiPanelDevice;
import Simulator.devices.interfaces.DeviceInterface;
import Simulator.listeners.DeviceListener;

/**
 * This Class represents a Solar panel. It has the needed functions and properties of a Solar panel
 * @created 11.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class Solarpanel extends GuiPanelDevice implements DeviceInterface {
	/**
	 * Serial ID for GUI Objects
	 */
	private static final long serialVersionUID = 1604082053613929393L;
	/**
	 * Index of the object Battery. It counts up everytime a object is created
	 */
	private static int ID = 0;
	/**
	 * Saves the ID of this object
	 */
	private int mID;
	/**
	 * listener class. For calling the EVents
	 */
	private DeviceListener listener;
	/**
	 * the instance of the current weather
	 */
	private Weather mWeather;
	/**
	 * the Max amount of the energy production(Watt Peak)
	 */
	private float mMaxEnergyProduction;
	/**
	 * Saves the Current Amount of energy output
	 */
	private float mCurrentEnergyOutput;
	/**
	 * Constructor of the current class
	 * @param listener needs the DeviceListener class for calling the events
	 * @param MaxEnergy the Watt Peak of the Solarpanel
	 */
	public Solarpanel(DeviceListener listener, float MaxEnergy){
		super("img/solarpanel.png");
		if(listener!=null){
			this.listener = listener;
			this.mID = Solarpanel.ID;
			Solarpanel.ID++;
			this.mMaxEnergyProduction = MaxEnergy;
			this.mWeather = Weather.getInstance();
			//register this object in the EnergyHandler
			this.listener.addSolarpanel(this);
		}
	}
	/**
	 * Remove the Solar Panel from the Collection
	 */
	public void DisconnectFromEnergyHandler(){
		this.listener.removeSolarpanel(this);
		Solarpanel.ID--;
	}
	/**
	 * calculates the current energy output
	 * @return the current energy output(Watt)
	 */
	public float getEnergyOutput(){
		float result;
		switch (this.mWeather.getCurrentWeather()) {
			case nice:
				//full power
				result = this.mMaxEnergyProduction;
				break;
			case cloudy:
				//when if it is cloudy the only the half of the power is produced
				result = this.mMaxEnergyProduction/2;
				break;
			case rainy:
				result = this.mMaxEnergyProduction/4;
				break;
			case night:
				//in the night no energy can be produced
				result = 0;
				break;
			default:
				//unknown selection return 0
				result = 0;
				break;
		}
		this.mCurrentEnergyOutput = result;
		this.repaint();
		return result;
	}
	/**
	 * This method is for drawing purpose, and its used for drawing the current Energy Output from the Solar panel
	 * @param g the graphics element which should be drawn
	 */
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth() - 1;
        //int h = getHeight() - 1;
        Graphics2D g2d = (Graphics2D) g;
		FontMetrics fm = g.getFontMetrics();
        int stringW = 0;
        int stringH = 0;
        g2d.setColor(Color.green);
        if (isEnabled()) {
        	String Output="";
        	NumberFormat numberFormat = new DecimalFormat("0.0");
            numberFormat.setRoundingMode(RoundingMode.DOWN);
        	try {
        		Output = ""+numberFormat.format(this.mCurrentEnergyOutput) +" Watt";
			} catch (Exception e) {
				// TODO: handle exception
				g2d.setColor(Color.black);
			}
        	
        	stringW = fm.stringWidth(Output);
            stringH = fm.getAscent();
            g2d.drawString(Output, (w-stringW)-stringW/2, stringH);
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
