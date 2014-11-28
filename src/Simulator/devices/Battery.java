package Simulator.devices;

import Simulator.tools.Joule;

public class Battery{
	public static int ID = 0;
	final int MAX_CAPACITY_AMPER_SECOND = 61200;
	private float mAmperSecond;
	private int mID;
	/**
	 * 
	 * @param energyInPercent
	 */
	public Battery(double energyInPercent){
		
	}
	/**
	 * 
	 * @param amper
	 */
	public void addAmper(float amper){
		
	}
	/**
	 * 
	 * @param amper
	 */
	public void setAmper(float amper){
		
	}
	/**
	 * 
	 * @param percent
	 */
	public void setAmperPercent(double percent){
		try {
			
		} catch (Exception e) {
			// if an error occurs the set the Battery level to 100%
			
		}
	}
	/**
	 * 
	 * @return
	 */
	public float getEnergy(){
		
		return 0;
	}
	/**
	 * 
	 * @return
	 */
	public double getEnergyPercent(){
		
		return 0;
	}
	
	public int getID(){
		return this.mID;
	}
}
