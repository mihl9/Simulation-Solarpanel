package Simulator;

import java.util.ArrayList;

import Simulator.devices.Batterie;
import Simulator.devices.Solarpanel;
import Simulator.devices.Abstract.ElectronicDevice;
import Simulator.listeners.DeviceListener;

public class EnergyHandler implements DeviceListener {
	private ArrayList<ElectronicDevice> mDevices;
	private ArrayList<Batterie> mBatteries;
	private ArrayList<Solarpanel> mSolarpanel;
	
	private Weather mWeather;
	private float mCurrentEnergy;
	private float mEnergyPerHour;
	
	public EnergyHandler(){
		
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
	public void addDevice(DeviceListener device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDevice(DeviceListener device) {
		// TODO Auto-generated method stub
		
	}

}
