package Simulator;

import java.util.ArrayList;

import Simulator.devices.Battery;
import Simulator.devices.Solarpanel;
import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;
import Simulator.tools.Joule;
import Simulator.tools.Joule.TimeUnit;
/**
 * @created 12.12.2014
 * @author Michael Huber
 * @version 1.0
 * The Controller Class for the whole functionality of the Simulation. This Class calculates the Energy use and several things
 */
public class EnergyHandler implements DeviceListener {
	/**
	 * The Voltage of the whole electronic circuit
	 */
	private final float CONST_VOLT = 12;
	/**
	 * Contains the Electronic Devices objects
	 */
	private ArrayList<ElectronicDevice> mDevices;
	/**
	 * Contains the Battery objects
	 */
	private ArrayList<Battery> mBatteries;
	/**
	 * Contains the Solar Panel objects
	 */
	private ArrayList<Solarpanel> mSolarpanel;
	/**
	 * The Instance of the Weather object
	 */
	private Weather mWeather;
	/**
	 * Summary of the whole generated Energy in this simulation
	 */
	private float mEnergySumGenerated=0;
	/**
	 * The current amount of Energy which is generated
	 */
	private float mEnergyGenerated=0;
	/**
	 * The current amount of Energy which is consumed
	 */
	private float mEnergyUse=0;
	/**
	 * The Current Battery Charge
	 */
	private float mEnergyReservoir=0;
	//private float mEnergyPerSecond;
	/**
	 * Constructor of the Class. It initializes all necessary objects
	 */
	public EnergyHandler(){
		//get the instance of the weather
		this.mWeather = Weather.getInstance();
		//initialize the objects
		mDevices = new ArrayList<ElectronicDevice>();
		mBatteries = new ArrayList<Battery>();
		mSolarpanel = new ArrayList<Solarpanel>();
	}
	/**
	 * Calculate the whole energy use of all connected devices
	 * @return the current Energy use (Watt)
	 */
	public float getCurrentEnergyUse(){
		float result=0;
		for(ElectronicDevice device : mDevices){
			result += device.getWattPower();
		}
		return result;
	}
	/**
	 * Calculates the whole energy which is created by all of the Solar Panels
	 * @return The Energy value (Watt)
	 */
	public float getCurrentSolarpanelsEnergyOutput(){
		float result=0;
		for(Solarpanel panel : mSolarpanel){
			result += panel.getEnergyOutput();
		}
		return result;
	}
	/**
	 * Calculates the Current Battery charge
	 * @return the battery charge in Ampere/Seconds
	 */
	public float getBatteryEnergyReservoir(){
		float result=0;
		for(Battery bat : mBatteries){
			result += bat.getAmpere(TimeUnit.s); 
		}
		return result;
	}
	/**
	 * Set the battery Charge of all Batteries to the given Value in Watt
	 * @param watt the amount which should be set (Watt)
	 */
	public void setWattInAllBatteries(float watt){
		for(Battery bat : mBatteries){
			bat.setAmpereSecond(Joule.calcAmperageWithWatt(watt, CONST_VOLT));
		}
	}
	/**
	 * Set the battery charge of all Batteries to the given percentage
	 * @param percent the percentage of the battery charge
	 */
	public void setChargePercentInAllBatteries(float percent){
		for(Battery bat : mBatteries){
			bat.setAmperePercent(percent);
		}
	}
	/**
	 * Calculates the number of watt seconds, getting to the battery
	 * @return the Current amount of Watt Seconds (W/s) 
	 */
	public float getWattPerSecond(){
		return getCurrentSolarpanelsEnergyOutput()-getCurrentEnergyUse();
	}
	/**
	 * Calculates the consumption and production of energy and add the reserves to the battery
	 */
	public void CheckEnergy(){
		float WattInTheBattery=0;
		float WattToAdd=0;
		//calculates the whole energy use
		this.mEnergySumGenerated += this.getCurrentSolarpanelsEnergyOutput();
		//get the current energy output
		this.mEnergyGenerated = this.getCurrentSolarpanelsEnergyOutput();
		WattToAdd = mEnergyGenerated/mBatteries.size();
		//go through each object and add the Energy
		for(Battery bat : mBatteries){
			bat.chargeBattery(Joule.calcAmperageWithWatt(WattToAdd, this.CONST_VOLT));
		}
		this.mEnergyReservoir = this.getBatteryEnergyReservoir();
		this.mEnergyUse = this.getCurrentEnergyUse();
		//calculate the current Performance which is in the Battery and subtract with the current energy use
		WattInTheBattery = Joule.calcPerformance(this.CONST_VOLT, this.mEnergyReservoir);
		WattInTheBattery -= this.mEnergyUse;
		//If the battery Charge is 0 or goes under 0 then shutdown all devices
		if(WattInTheBattery<=0){
			turnOffAllDevices();
			setWattInAllBatteries(0);
		}else{
			//setWattInAllBatteries(WattInTheBattery/mBatteries.size());
			for(Battery bat : mBatteries){
				bat.dischargeBattery(Joule.calcAmperageWithWatt(this.mEnergyUse/mBatteries.size(), this.CONST_VOLT));
			}
		}
		
	}
	/**
	 * Turn of all device at once
	 */
	public void turnOffAllDevices(){
		for(ElectronicDevice devices : mDevices){
			devices.setDeviceRunning(false);
		}
	}
	/**
	 * Add the given device into the ArrayList
	 */
	@Override
	public void addDevice(ElectronicDevice device) {
		// when a device is created this event is triggered
		this.mDevices.add(device.getID(),device);
		System.out.println("Device with the ID:" + device.getID() + " was registered");
	}
	/**
	 * remove the given device from the ArrayList
	 */
	@Override
	public void removeDevice(ElectronicDevice device) {
		// when a battery is removed this event is triggered
		this.mDevices.remove(device.getID());
	}
	/**
	 * Add the given battery into the ArrayList
	 */
	@Override
	public void addBattery(Battery battery) {
		// when a battery is created this event is triggered
		this.mBatteries.add(battery.getID(),battery);
		System.out.println("Battery with the ID:" + battery.getID() + " was registered");
	}
	/**
	 * remove the given battery from the ArrayList
	 */
	@Override
	public void removeBattery(Battery battery) {
		// when a battery is created this event is triggered
		this.mBatteries.remove(battery.getID());
	}
	/**
	 * Add the given Solar Panel into the ArrayList
	 */
	@Override
	public void addSolarpanel(Solarpanel solarpanel) {
		// when a solarpanel is created this event is triggered
		this.mSolarpanel.add(solarpanel.getID(),solarpanel);
		System.out.println("Solarpanel with the ID:" + solarpanel.getID() + " was registered");
	}
	/**
	 * remove the given Solar Panel from the ArrayList
	 */
	@Override
	public void removeSolarpanel(Solarpanel solarpanel) {
		// when a solarpanel is removed this event is triggered
		this.mSolarpanel.remove(solarpanel.getID());
	}

}
