package Simulator;

import java.util.ArrayList;

import Simulator.devices.Battery;
import Simulator.devices.Solarpanel;
import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;

public class EnergyHandler implements DeviceListener {
	private ArrayList<ElectronicDevice> mDevices;
	private ArrayList<Battery> mBatteries;
	private ArrayList<Solarpanel> mSolarpanel;
	
	private Weather mWeather;
	private float mCurrentEnergy;
	private float mEnergyPerHour;
	
	public EnergyHandler(){
		mDevices = new ArrayList<ElectronicDevice>();
		mBatteries = new ArrayList<Battery>();
		
	}
	
	public float getCurrentEnergyUse(){
		// TODO Programmieren
		return 0;
	}
	
	public float getEnergyPerHour(){
		// TODO Programmieren
		return 0;
	}
	
	public void CheckEnergy(){
		// TODO Programmieren. Diese Funktion berrechnet die Energy Leistung und den Verbrauch
	}
	
	@Override
	public void addDevice(ElectronicDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDevice(ElectronicDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBattery(Battery battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBattery(Battery battery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSolarpanel(Solarpanel solarpanel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSolarpanel(Solarpanel solarpanel) {
		// TODO Auto-generated method stub
		
	}

}
