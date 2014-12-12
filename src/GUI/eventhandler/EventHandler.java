package GUI.eventhandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.SimulationGUI;
import Simulator.EnergyHandler;
import Simulator.Weather.weatherTyp;
import Simulator.devices.Battery;
import Simulator.devices.Hotplate;
import Simulator.devices.Lamp;
import Simulator.devices.Radiator;
import Simulator.devices.Solarpanel;

public class EventHandler implements ActionListener, ChangeListener{

	private SimulationGUI mfrmSimulation;
	private EnergyHandler mEnergyHandler;
	
	public EventHandler(SimulationGUI myGui, EnergyHandler handler){
		this.mfrmSimulation = myGui;
		this.mEnergyHandler = handler;
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.mfrmSimulation.getTxtBatteryCharge().setText(Integer.toString(((JSlider)arg0.getSource()).getValue()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(this.mfrmSimulation.getBtnStart().getText())){
			int amountBat;
			int amountSolarPanels;
			int amountLamp;
			int amountHotplate;
			int amountRadiator;
			int batteryCharge;
			int i=0;
			amountBat = Integer.parseInt(this.mfrmSimulation.getTxtAmountBattery().getText());
			amountSolarPanels = Integer.parseInt(this.mfrmSimulation.getTxtAmountSolarPanel().getText());
			amountLamp = Integer.parseInt(this.mfrmSimulation.getTxtAmountLamp().getText());
			amountHotplate = Integer.parseInt(this.mfrmSimulation.getTxtAmountHotplate().getText());
			amountRadiator = Integer.parseInt(this.mfrmSimulation.getTxtAmountRadiator().getText());
			batteryCharge = Integer.parseInt(this.mfrmSimulation.getTxtBatteryCharge().getText());
			if(ValidateAmountOfDevices(amountBat,"Batterie")){
				if(ValidateAmountOfDevices(amountSolarPanels, "Solar Panel")){
					if(ValidateAmountOfDevices(amountLamp, "Lampe")){
						if(ValidateAmountOfDevices(amountHotplate, "Herdplatte")){
							if(ValidateAmountOfDevices(amountRadiator, "Heizung")){
								//all of the option is set
								i=0;
								while(i<amountSolarPanels){
									//creates the Solar Panel object
									this.mfrmSimulation.getPnlSolarPanel().add(new Solarpanel(this.mEnergyHandler, 20));
									i++;
								}
								i=0;
								while(i<amountBat){
									//creates the Battery object
									this.mfrmSimulation.getPnlBatteries().add(new Battery(this.mEnergyHandler, batteryCharge));
									i++;
								}
								i=0;
								while(i<amountLamp){
									//creates the Lamp object
									this.mfrmSimulation.getPnlLamp().add(new Lamp(this.mEnergyHandler, 20));
								}
								i=0;
								while(i<amountHotplate){
									//creates the Hotplate object
									this.mfrmSimulation.getPnlHotplate().add(new Hotplate(this.mEnergyHandler, 50, 3));
								}
								while(i<amountRadiator){
									//creates the Radiator object
									this.mfrmSimulation.getPnlRadiator().add(new Radiator(this.mEnergyHandler, 100, 3));
								}
								//set the weather based on the selection
								this.mEnergyHandler.setWeather((weatherTyp)this.mfrmSimulation.getCboWeather().getSelectedItem());
								System.out.println("Simulation wurde gestartet");
								this.mfrmSimulation.getTimer().start();
							}
						}
					}
				}
			}
			
		}
		
		if(e.getActionCommand().equals(this.mfrmSimulation.getBtnStop().getText())){
			System.out.println("Simulation wurde gestopt");
			this.mfrmSimulation.getTimer().start();
		}
		
		if(e.getActionCommand().equals(this.mfrmSimulation.getBtnSetBatteryCharge())){
			if(Integer.parseInt(this.mfrmSimulation.getTxtBatteryCharge().getText())>= 0 && Integer.parseInt(this.mfrmSimulation.getTxtBatteryCharge().getText())<=100){
				this.mEnergyHandler.setChargePercentInAllBatteries(Integer.parseInt(this.mfrmSimulation.getTxtBatteryCharge().getText()));
			}
		}
	}

	private boolean ValidateAmountOfDevices(int amount, String DeviceName){
		boolean result = false;
		if(amount >=1 && amount <=7){
			result = true;
		}else{
			JOptionPane.showMessageDialog(null, "Fehlerhafte anzahl von " + DeviceName + ". Bitte geben Sie eine neue Anzahl ein.","Fehler",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
}
