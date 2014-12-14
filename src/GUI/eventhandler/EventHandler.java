package GUI.eventhandler;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.CalculatorGUI;
import GUI.SimulationGUI;
import Simulator.EnergyHandler;
import Simulator.Weather.weatherTyp;
import Simulator.devices.Battery;
import Simulator.devices.Hotplate;
import Simulator.devices.Lamp;
import Simulator.devices.Radiator;
import Simulator.devices.Solarpanel;
import Simulator.devices.interfaces.DeviceInterface;
/**
 * This Class is the Event handler for the Actions in the Simulator GUI.
 * @created 13.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class EventHandler implements ActionListener, ChangeListener{
	/**
	 * this value saves the instance of the GUI
	 */
	private SimulationGUI mfrmSimulation;
	/**
	 * this value saves the instance of the Energy Handler
	 */
	private EnergyHandler mEnergyHandler;
	/**
	 * Constructor of this class.
	 * @param myGui  the Instance of the GUI
	 * @param handler the Instance of the current Energy handler
	 */
	public EventHandler(SimulationGUI myGui, EnergyHandler handler){
		this.mfrmSimulation = myGui;
		this.mEnergyHandler = handler;
	}
	/**
	 * This Event is called when any change in the GUI happens
	 * @param arg0 the information object of the triggered event
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.mfrmSimulation.getTxtBatteryCharge().setText(Integer.toString(((JSlider)arg0.getSource()).getValue()));
	}
	/**
	 * this event is called when any action is made in the gui
	 * @param e The Information object about the Action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// start button is pressed
		if(e.getActionCommand().equals(this.mfrmSimulation.getBtnStart().getText())){
			int amountBat;
			int amountSolarPanels;
			int amountLamp;
			int amountHotplate;
			int amountRadiator;
			int batteryCharge;
			int i=0;
			try {
				amountBat = Integer.parseInt(this.mfrmSimulation.getTxtAmountBattery().getText());
				amountSolarPanels = Integer.parseInt(this.mfrmSimulation.getTxtAmountSolarPanel().getText());
				amountLamp = Integer.parseInt(this.mfrmSimulation.getTxtAmountLamp().getText());
				amountHotplate = Integer.parseInt(this.mfrmSimulation.getTxtAmountHotplate().getText());
				amountRadiator = Integer.parseInt(this.mfrmSimulation.getTxtAmountRadiator().getText());
				batteryCharge = Integer.parseInt(this.mfrmSimulation.getTxtBatteryCharge().getText());
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Fehlerhafte eingabe. Bitte geben Sie eine gültige Zahl ein.","Fehler",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(ValidateAmountOfDevices(amountBat,1,7,"Batterie")){
				if(ValidateAmountOfDevices(amountSolarPanels,1,7, "Solar Panel")){
					if(ValidateAmountOfDevices(amountLamp,0,7, "Lampe")){
						if(ValidateAmountOfDevices(amountHotplate,0,7, "Herdplatte")){
							if(ValidateAmountOfDevices(amountRadiator,0,7, "Heizung")){
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
									i++;
								}
								i=0;
								while(i<amountHotplate){
									//creates the Hotplate object
									this.mfrmSimulation.getPnlHotplate().add(new Hotplate(this.mEnergyHandler, 50, 3));
									i++;
								}
								i=0;
								while(i<amountRadiator){
									//creates the Radiator object
									this.mfrmSimulation.getPnlRadiator().add(new Radiator(this.mEnergyHandler, 100, 3));
									i++;
								}
								//set the weather based on the selection
								this.mEnergyHandler.setWeather((weatherTyp)this.mfrmSimulation.getCboWeather().getSelectedItem());
								System.out.println("Simulation was started");
								this.mfrmSimulation.getTxtRuntime().setText("0");
								this.setOptionsEditable(false);
								this.mfrmSimulation.getTimer().start();
								this.mfrmSimulation.revalidate();
								this.mfrmSimulation.repaint();
							}
						}
					}
				}
			}
			
		}
		//stop button is pressed
		if(e.getActionCommand().equals(this.mfrmSimulation.getBtnStop().getText())){
			System.out.println("Simulation was stopped");
			this.removeAllComponents(this.mfrmSimulation.getPnlSolarPanel());
			this.removeAllComponents(this.mfrmSimulation.getPnlBatteries());
			this.removeAllComponents(this.mfrmSimulation.getPnlLamp());
			this.removeAllComponents(this.mfrmSimulation.getPnlHotplate());
			this.removeAllComponents(this.mfrmSimulation.getPnlRadiator());
			this.mEnergyHandler.resetTotalGeneratedEnergy();
			this.mfrmSimulation.revalidate();
			this.mfrmSimulation.repaint();
			this.mfrmSimulation.getTimer().stop();
			this.setOptionsEditable(true);
			//stop procedure
		}
		//Pause button is pressed
		if(e.getActionCommand().equals(this.mfrmSimulation.getBtnPause().getText())){
			int i=0;
			if(e.getActionCommand().equals("Pause")){
				this.mfrmSimulation.getTimer().stop();
				this.mfrmSimulation.getBtnPause().setText("Resume");
				
				this.setOptionsEditable(true);
				this.mfrmSimulation.getTxtAmountBattery().setEnabled(false);
				this.mfrmSimulation.getBtnStart().setEnabled(false);
				this.mfrmSimulation.getBtnPause().setEnabled(true);
			}else{
				int amountLamp;
				int amountHotplate;
				int amountRadiator;
				int amountSolarPanels;
				try {
					amountLamp = Integer.parseInt(this.mfrmSimulation.getTxtAmountLamp().getText());
					amountHotplate = Integer.parseInt(this.mfrmSimulation.getTxtAmountHotplate().getText());
					amountRadiator = Integer.parseInt(this.mfrmSimulation.getTxtAmountRadiator().getText());
					amountSolarPanels = Integer.parseInt(this.mfrmSimulation.getTxtAmountSolarPanel().getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Fehlerhafte eingabe. Bitte geben Sie eine gültige Zahl ein.","Fehler",JOptionPane.ERROR_MESSAGE);
					return;
				}
				this.mfrmSimulation.getBtnPause().setText("Pause");
				//check if the amount of Lamp has changed
				if(this.mfrmSimulation.getPnlLamp().getComponentCount()!=amountLamp){
					this.removeAllComponents(this.mfrmSimulation.getPnlLamp());
					i=0;
					while(i<amountLamp && i<=7){
						//creates the Lamp object
						this.mfrmSimulation.getPnlLamp().add(new Lamp(this.mEnergyHandler, 20));
						i++;
					}
				}
				//check if the amount of Hotplates has changed
				if(this.mfrmSimulation.getPnlHotplate().getComponentCount()!=amountHotplate){
					this.removeAllComponents(this.mfrmSimulation.getPnlHotplate());
					i=0;
					while(i<amountHotplate && i<=7){
						//creates the Hotplate object
						this.mfrmSimulation.getPnlHotplate().add(new Hotplate(this.mEnergyHandler, 50, 3));
						i++;
					}
				}
				//check if the amount of Radiators has changed
				if(this.mfrmSimulation.getPnlRadiator().getComponentCount()!=amountRadiator){
					this.removeAllComponents(this.mfrmSimulation.getPnlRadiator());
					i=0;
					while(i<amountRadiator && i<=7){
						//creates the Radiator object
						this.mfrmSimulation.getPnlRadiator().add(new Radiator(this.mEnergyHandler, 100, 3));
						i++;
					}
				}
				//check if the amount of Solarpanels has changed
				if(this.mfrmSimulation.getPnlSolarPanel().getComponentCount()!=amountSolarPanels){
					this.removeAllComponents(this.mfrmSimulation.getPnlSolarPanel());
					i=0;
					while(i<amountSolarPanels && i<=7){
						//creates the Solar Panel object
						this.mfrmSimulation.getPnlSolarPanel().add(new Solarpanel(this.mEnergyHandler, 20));
						i++;
					}
				}
				this.setOptionsEditable(false);
				this.mfrmSimulation.revalidate();
				this.mfrmSimulation.repaint();
				this.mfrmSimulation.getTimer().start();
			}
			
			
		}
		//
		if(e.getActionCommand().equals(this.mfrmSimulation.getBtnSetBatteryCharge().getText())){
			if(Integer.parseInt(this.mfrmSimulation.getTxtBatteryCharge().getText())>= 0 && Integer.parseInt(this.mfrmSimulation.getTxtBatteryCharge().getText())<=100){
				this.mEnergyHandler.setChargePercentInAllBatteries(Integer.parseInt(this.mfrmSimulation.getTxtBatteryCharge().getText()));
			}
		}
		//the weather combobox has changes
		if(e.getActionCommand().equals("comboBoxChanged")){
			//set the weather based on the selection
			this.mEnergyHandler.setWeather((weatherTyp)this.mfrmSimulation.getCboWeather().getSelectedItem());
		}
		//the calculator button is pressed
		if(e.getActionCommand().equals(this.mfrmSimulation.getBtnCalculator().getText())){
			new CalculatorGUI();
		}
	}
	/**
	 * Set the editability of the Setting fields based on the value
	 * @param editable the value which indicates if the Fields should be editable or not
	 */
	private void setOptionsEditable(boolean editable){
		this.mfrmSimulation.getTxtAmountBattery().setEnabled(editable);
		this.mfrmSimulation.getTxtAmountHotplate().setEnabled(editable);
		this.mfrmSimulation.getTxtAmountLamp().setEnabled(editable);
		this.mfrmSimulation.getTxtAmountRadiator().setEnabled(editable);
		this.mfrmSimulation.getTxtAmountSolarPanel().setEnabled(editable);
		this.mfrmSimulation.getBtnStart().setEnabled(editable);
		this.mfrmSimulation.getBtnStop().setEnabled(!editable);
		this.mfrmSimulation.getBtnPause().setEnabled(!editable);
	}
	/**
	 * Remove all device from the Container and disconnect the object from the Energy handler
	 * @param target the Container which inherits the object
	 */
	private void removeAllComponents(JPanel target){
		for(Component object : target.getComponents()){
			((DeviceInterface) object).DisconnectFromEnergyHandler();
		}
		target.removeAll();
	}
	/**
	 * Validator for the amount of objects which should be created by the procedure
	 * @param amount the Amount of object which should be created
	 * @param min  the min amount of objects
	 * @param max the max amount of objects
	 * @param DeviceName the name of the Device
	 * @return a boolean value depending if it is a correct value
	 */
	private boolean ValidateAmountOfDevices(int amount,int min,int max, String DeviceName){
		boolean result = false;
		if(amount >=min && amount <=max){
			result = true;
		}else{
			JOptionPane.showMessageDialog(null, "Fehlerhafte anzahl von " + DeviceName + ". Bitte geben Sie eine neue Anzahl ein.","Fehler",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
}
