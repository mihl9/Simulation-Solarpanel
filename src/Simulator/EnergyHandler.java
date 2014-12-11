package Simulator;

import java.util.ArrayList;

import Simulator.devices.Battery;
import Simulator.devices.Solarpanel;
import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;
import Simulator.tools.Joule;
import Simulator.tools.Joule.TimeUnit;

public class EnergyHandler implements DeviceListener {
	private final float CONST_VOLT = 12;
	
	private ArrayList<ElectronicDevice> mDevices;
	private ArrayList<Battery> mBatteries;
	private ArrayList<Solarpanel> mSolarpanel;
	
	private Weather mWeather;
	private float mEnergySumGenerated=0;
	private float mEnergyGenerated=0;
	private float mEnergyUse=0;
	private float mEnergyReservoir=0;
	//private float mEnergyPerSecond;
	
	public EnergyHandler(){
		//get the instance of the weather
		this.mWeather = Weather.getInstance();
		//initialize the objects
		mDevices = new ArrayList<ElectronicDevice>();
		mBatteries = new ArrayList<Battery>();
		mSolarpanel = new ArrayList<Solarpanel>();
	}
	
	public float getCurrentEnergyUse(){
		float result=0;
		for(ElectronicDevice device : mDevices){
			result += device.getWattPower();
		}
		return result;
	}
	
	public float getCurrentSolarpanelsEnergyOutput(){
		float result=0;
		for(Solarpanel panel : mSolarpanel){
			result += panel.getEnergyOutput();
		}
		return result;
	}
	
	public float getBatteryEnergyReservoir(){
		float result=0;
		for(Battery bat : mBatteries){
			result += bat.getAmpere(TimeUnit.s); 
		}
		return result;
	}
	
	public void setWattInAllBatteries(float watt){
		for(Battery bat : mBatteries){
			bat.setAmpereSecond(Joule.calcAmperageWithWatt(watt, CONST_VOLT));
		}
	}
	
	public void setChargePercentInAllBatteries(){
		
	}
	public float getWattPerSecond(){
		// TODO Programmieren
		return getCurrentSolarpanelsEnergyOutput()-getCurrentEnergyUse();
	}
	
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
			bat.addAmpereSecond(Joule.calcAmperageWithWatt(WattToAdd, this.CONST_VOLT));
		}
		this.mEnergyReservoir = this.getBatteryEnergyReservoir();
		this.mEnergyUse = this.getCurrentEnergyUse();
		WattInTheBattery = Joule.calcPerformance(this.CONST_VOLT, this.mEnergyReservoir);
		WattInTheBattery -= this.mEnergyUse;
		if(WattInTheBattery<=0){
			turnOffAllDevices();
			setWattInAllBatteries(0);
		}else{
			setWattInAllBatteries(WattInTheBattery/mBatteries.size());
		}
		
	}
	
	public void turnOffAllDevices(){
		for(ElectronicDevice devices : mDevices){
			devices.setDeviceRunning(false);
		}
	}
	@Override
	public void addDevice(ElectronicDevice device) {
		// when a device is created this event is triggered
		this.mDevices.add(device.getID(),device);
		System.out.println("Device with the ID:" + device.getID() + " was registered");
	}

	@Override
	public void removeDevice(ElectronicDevice device) {
		// when a battery is removed this event is triggered
		this.mDevices.remove(device.getID());
	}

	@Override
	public void addBattery(Battery battery) {
		// when a battery is created this event is triggered
		this.mBatteries.add(battery.getID(),battery);
		System.out.println("Battery with the ID:" + battery.getID() + " was registered");
	}

	@Override
	public void removeBattery(Battery battery) {
		// when a battery is created this event is triggered
		this.mBatteries.remove(battery.getID());
	}

	@Override
	public void addSolarpanel(Solarpanel solarpanel) {
		// when a solarpanel is created this event is triggered
		this.mSolarpanel.add(solarpanel.getID(),solarpanel);
		System.out.println("Solarpanel with the ID:" + solarpanel.getID() + " was registered");
	}

	@Override
	public void removeSolarpanel(Solarpanel solarpanel) {
		// when a solarpanel is removed this event is triggered
		this.mSolarpanel.remove(solarpanel.getID());
	}

}
