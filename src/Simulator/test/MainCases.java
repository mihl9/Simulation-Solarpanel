package Simulator.test;

import static org.junit.Assert.*;


import org.junit.Test;

import GUI.CalculatorGUI;
import Simulator.EnergyHandler;
import Simulator.Weather.weatherTyp;
import Simulator.devices.Battery;
import Simulator.devices.Solarpanel;
import Simulator.tools.Joule;

public class MainCases {

	@Test
	public void testGetEnergyOutput() {
		EnergyHandler energyhandler = new EnergyHandler();
		Solarpanel solpanel = new Solarpanel(energyhandler, 20);
		
		energyhandler.setWeather(weatherTyp.nice);
		assertEquals(20.0, solpanel.getEnergyOutput(),0.1);
		energyhandler.setWeather(weatherTyp.cloudy);
		assertEquals(10.0, solpanel.getEnergyOutput(),0.1);
		energyhandler.setWeather(weatherTyp.rainy);
		assertEquals(5.0, solpanel.getEnergyOutput(),0.1);
		energyhandler.setWeather(weatherTyp.night);
		assertEquals(0.0, solpanel.getEnergyOutput(),0.1);
		
		solpanel.DisconnectFromEnergyHandler();
		energyhandler=null;
	}
	
	
	
	@Test
	public void testSetAmperePercent(){
		EnergyHandler energyhandler = new EnergyHandler();
		Battery bat = new Battery(energyhandler, 100);
		Solarpanel solarpanel = new Solarpanel(energyhandler, 20);
		
		assertTrue(bat.setAmperePercent(50));
		assertTrue(bat.setAmperePercent(100));
		assertFalse(bat.setAmperePercent(123));
		
		bat.DisconnectFromEnergyHandler();
		solarpanel.DisconnectFromEnergyHandler();
		energyhandler=null;
	}
	
	@Test
	public void testCalcTime(){
		CalculatorGUI calc = new CalculatorGUI();
		calc.setVisible(false);
		assertEquals(Joule.convertTimeUnit(5.0f, Simulator.tools.Joule.TimeUnit.m, Simulator.tools.Joule.TimeUnit.s), calc.CalcTime(1, 5, 20), 0.1);
		assertEquals(-1, calc.CalcTime(-1, -1.0f, -1.01f),0.1);
		assertEquals(-1, calc.CalcTime(0, 0, 0),0.1);
		calc.dispose();
		calc = null;
	}
	
	@Test
	public void testSetTotalGeneratedEnergy(){
		EnergyHandler energyHandler = new EnergyHandler();
		Battery battery = new Battery(energyHandler, 0);
		Solarpanel solpanel = new Solarpanel(energyHandler, 20);
		//tick 5 seconds
		energyHandler.CheckEnergy();
		energyHandler.CheckEnergy();
		energyHandler.CheckEnergy();
		energyHandler.CheckEnergy();
		energyHandler.CheckEnergy();
		
		assertEquals(100.0f, energyHandler.getTotalGeneratedEnergy(),0.1f);
		
		energyHandler.setTotalGeneratedEnergy(-123);
		assertEquals(100.0f, energyHandler.getTotalGeneratedEnergy(),0.1f);
		
		energyHandler.setTotalGeneratedEnergy(0);
		assertEquals(0.0f, energyHandler.getTotalGeneratedEnergy(),0.1f);
		battery.DisconnectFromEnergyHandler();
		solpanel.DisconnectFromEnergyHandler();
		energyHandler=null;
	}
	
	@Test
	public void testGetRemainingTime(){
		EnergyHandler menergyHandler = new EnergyHandler();
		Battery battery = new Battery(menergyHandler, 0);
		Solarpanel solpanel = new Solarpanel(menergyHandler, 20);
		//tick
		menergyHandler.CheckEnergy();
		
		assertEquals(38879.0f,battery.getRemainingTime(),0.1f);
		
		battery.DisconnectFromEnergyHandler();
		solpanel.DisconnectFromEnergyHandler();
		menergyHandler=null;
	}
}
