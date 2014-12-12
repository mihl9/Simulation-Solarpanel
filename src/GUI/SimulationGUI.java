package GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import GUI.eventhandler.EventHandler;
import GUI.eventhandler.TimerHandler;
import Simulator.EnergyHandler;
import Simulator.Weather.weatherTyp;

public class SimulationGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7082270711707837583L;
	//Testfields
	private JTextField txtAmountBattery;
	private JTextField txtAmountSolarPanel;
	private JTextField txtAmountLamp;
	private JTextField txtAmountHotplate;
	private JTextField txtAmountRadiator;
	private JTextField txtBatteryCharge;
	//panels
	private JPanel pnlSolarPanel;
	private JPanel pnlBatteries;
	private JPanel pnlLamp;
	private JPanel pnlHotplate;
	private JPanel pnlRadiator;
	//buttons
	private JButton btnSetBatteryCharge;
	private JButton btnStart;
	private JButton btnStop;
	//combobox
	private JComboBox cboWeather;
	//slider
	private JSlider slrBatteryCharge;
	
	private EventHandler mEventHandler;
	private TimerHandler mTimerHandler;
	private EnergyHandler mEnergyHandler;
	private Timer mTimer;
	public SimulationGUI() {
		//auto generated part
		//used with the GUI Creator because i was to lazy
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 400);
		getContentPane().setLayout(null);
		
		JPanel pnlController = new JPanel();
		pnlController.setBounds(22, 11, 186, 323);
		getContentPane().add(pnlController);
		pnlController.setLayout(null);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(10, 289, 73, 23);
		pnlController.add(btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(91, 289, 80, 23);
		pnlController.add(btnStop);
		
		txtAmountBattery = new JTextField();
		txtAmountBattery.setBounds(125, 11, 46, 20);
		pnlController.add(txtAmountBattery);
		txtAmountBattery.setColumns(10);
		
		JLabel lblAnzahlBatterien = new JLabel("Anzahl Batterien");
		lblAnzahlBatterien.setLabelFor(txtAmountBattery);
		lblAnzahlBatterien.setBounds(10, 14, 86, 14);
		pnlController.add(lblAnzahlBatterien);
		
		JLabel lblAnzahlSolarpanel = new JLabel("Anzahl Solarpanel");
		lblAnzahlSolarpanel.setBounds(10, 143, 86, 14);
		pnlController.add(lblAnzahlSolarpanel);
		
		txtAmountSolarPanel = new JTextField();
		lblAnzahlSolarpanel.setLabelFor(txtAmountSolarPanel);
		txtAmountSolarPanel.setColumns(10);
		txtAmountSolarPanel.setBounds(125, 140, 46, 20);
		pnlController.add(txtAmountSolarPanel);
		
		JLabel lblAnzahlLampen = new JLabel("Anzahl Lampen");
		lblAnzahlLampen.setBounds(10, 171, 86, 14);
		pnlController.add(lblAnzahlLampen);
		
		txtAmountLamp = new JTextField();
		lblAnzahlLampen.setLabelFor(txtAmountLamp);
		txtAmountLamp.setColumns(10);
		txtAmountLamp.setBounds(125, 168, 46, 20);
		pnlController.add(txtAmountLamp);
		
		txtAmountHotplate = new JTextField();
		txtAmountHotplate.setColumns(10);
		txtAmountHotplate.setBounds(125, 196, 46, 20);
		pnlController.add(txtAmountHotplate);
		
		JLabel lblAnzahlHerdplatten = new JLabel("Anzahl Herdplatten");
		lblAnzahlHerdplatten.setLabelFor(txtAmountHotplate);
		lblAnzahlHerdplatten.setBounds(10, 199, 105, 14);
		pnlController.add(lblAnzahlHerdplatten);
		
		JLabel lblAnzahlHeizungen = new JLabel("Anzahl Heizungen");
		lblAnzahlHeizungen.setBounds(10, 230, 105, 14);
		pnlController.add(lblAnzahlHeizungen);
		
		txtAmountRadiator = new JTextField();
		lblAnzahlHeizungen.setLabelFor(txtAmountRadiator);
		txtAmountRadiator.setColumns(10);
		txtAmountRadiator.setBounds(125, 227, 46, 20);
		pnlController.add(txtAmountRadiator);
		
		slrBatteryCharge = new JSlider();
		slrBatteryCharge.setBounds(10, 78, 161, 23);
		pnlController.add(slrBatteryCharge);
		
		txtBatteryCharge = new JTextField();
		txtBatteryCharge.setText("50");
		txtBatteryCharge.setEditable(false);
		txtBatteryCharge.setColumns(10);
		txtBatteryCharge.setBounds(125, 47, 46, 20);
		pnlController.add(txtBatteryCharge);
		
		JLabel lblBatterieLadung = new JLabel("Batterie Ladung");
		lblBatterieLadung.setLabelFor(txtBatteryCharge);
		lblBatterieLadung.setBounds(10, 50, 86, 14);
		pnlController.add(lblBatterieLadung);
		
		cboWeather = new JComboBox();
		cboWeather.setModel(new DefaultComboBoxModel(weatherTyp.values()));
		cboWeather.setBounds(66, 258, 105, 20);
		pnlController.add(cboWeather);
		
		JLabel lblWetter = new JLabel("Wetter");
		lblWetter.setBounds(10, 261, 46, 14);
		pnlController.add(lblWetter);
		
		btnSetBatteryCharge = new JButton("Ladung Setzen");
		btnSetBatteryCharge.setBounds(66, 106, 105, 23);
		pnlController.add(btnSetBatteryCharge);
		
		JPanel pnlSimulation = new JPanel();
		pnlSimulation.setBounds(229, 11, 638, 309);
		getContentPane().add(pnlSimulation);
		pnlSimulation.setLayout(null);
		
		JLabel lblSolarPanels = new JLabel("Solar Panels");
		lblSolarPanels.setBounds(10, 11, 64, 14);
		pnlSimulation.add(lblSolarPanels);
		
		pnlSolarPanel = new JPanel();
		pnlSolarPanel.setBounds(10, 36, 114, 262);
		pnlSimulation.add(pnlSolarPanel);
		pnlSolarPanel.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lbBatteries = new JLabel("Batterien");
		lbBatteries.setBounds(134, 11, 64, 14);
		pnlSimulation.add(lbBatteries);
		
		pnlBatteries = new JPanel();
		pnlBatteries.setBounds(134, 36, 114, 262);
		pnlSimulation.add(pnlBatteries);
		pnlBatteries.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lbLamp = new JLabel("Lampen");
		lbLamp.setBounds(258, 11, 64, 14);
		pnlSimulation.add(lbLamp);
		
		pnlLamp = new JPanel();
		pnlLamp.setBounds(258, 36, 114, 262);
		pnlSimulation.add(pnlLamp);
		pnlLamp.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lbHotplate = new JLabel("Herdplatten");
		lbHotplate.setBounds(382, 11, 64, 14);
		pnlSimulation.add(lbHotplate);
		
		pnlHotplate = new JPanel();
		pnlHotplate.setBounds(382, 36, 114, 262);
		pnlSimulation.add(pnlHotplate);
		pnlHotplate.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lbRadiator = new JLabel("Heizung");
		lbRadiator.setBounds(506, 11, 64, 14);
		pnlSimulation.add(lbRadiator);
		
		pnlRadiator = new JPanel();
		pnlRadiator.setBounds(506, 36, 114, 262);
		pnlSimulation.add(pnlRadiator);
		pnlRadiator.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lblEinstellungen = new JLabel("Einstellungen");
		lblEinstellungen.setBounds(22, 0, 71, 14);
		getContentPane().add(lblEinstellungen);
		
		JLabel lblSimulation = new JLabel("Simulation");
		lblSimulation.setBounds(229, 0, 71, 14);
		getContentPane().add(lblSimulation);
		
		//end of the auto generated code
		this.mEnergyHandler = new EnergyHandler();
		this.mEventHandler = new EventHandler(this, mEnergyHandler);
		//Add the ELements to the Eventhandler
		this.mTimer = new Timer(1000, mTimerHandler);
		btnStart.addActionListener(mEventHandler);
		btnStop.addActionListener(mEventHandler);
		btnSetBatteryCharge.addActionListener(mEventHandler);
		cboWeather.addActionListener(mEventHandler);
		slrBatteryCharge.addChangeListener(mEventHandler);
		setVisible(true);
		
	}
	public JTextField getTxtAmountBattery() {
		return txtAmountBattery;
	}
	public JTextField getTxtAmountSolarPanel() {
		return txtAmountSolarPanel;
	}
	public JTextField getTxtAmountLamp() {
		return txtAmountLamp;
	}
	public JTextField getTxtAmountHotplate() {
		return txtAmountHotplate;
	}
	public JTextField getTxtAmountRadiator() {
		return txtAmountRadiator;
	}
	public JTextField getTxtBatteryCharge() {
		return txtBatteryCharge;
	}
	public JPanel getPnlSolarPanel() {
		return pnlSolarPanel;
	}
	public JPanel getPnlBatteries() {
		return pnlBatteries;
	}
	public JPanel getPnlLamp() {
		return pnlLamp;
	}
	public JPanel getPnlHotplate() {
		return pnlHotplate;
	}
	public JPanel getPnlRadiator() {
		return pnlRadiator;
	}
	public Timer getTimer(){
		return mTimer;
	}
	public JButton getBtnSetBatteryCharge() {
		return btnSetBatteryCharge;
	}
	public JButton getBtnStart() {
		return btnStart;
	}
	public JButton getBtnStop() {
		return btnStop;
	}
	public JComboBox getCboWeather() {
		return cboWeather;
	}
	public JSlider getSlrBatteryCharge() {
		return slrBatteryCharge;
	}
	
}
