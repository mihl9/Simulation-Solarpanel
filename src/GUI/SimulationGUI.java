package GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import GUI.eventhandler.EventHandler;
import GUI.eventhandler.TimerHandler;
import Simulator.EnergyHandler;
import Simulator.Weather.weatherTyp;

import javax.swing.border.LineBorder;

import java.awt.Color;
/**
 * the class of the Simulation GUI. the Purpose for this GUI is to show the current state of the simulation
 * @created 14.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class SimulationGUI extends JFrame {
	/**
	 * The startpoint of the Application
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//SimulationHouse simulation = new SimulationHouse();
		@SuppressWarnings("unused")
		SimulationGUI gui;
		gui = new SimulationGUI();
	}
	
	/**
	 * The Serial object for the GUI object
	 */
	private static final long serialVersionUID = -7082270711707837583L;
	//Text fields
	/**
	 * The text field object which inherits the amount of Batteries
	 */
	private JTextField txtAmountBattery;
	/**
	 * The text field object which inherits the amount of Solar panel
	 */
	private JTextField txtAmountSolarPanel;
	/**
	 * The text field object which inherits the amount of lamp
	 */
	private JTextField txtAmountLamp;
	/**
	 * The text field object which inherits the amount of hotplates
	 */
	private JTextField txtAmountHotplate;
	/**
	 * The text field object which inherits the amount of radiator
	 */
	private JTextField txtAmountRadiator;
	/**
	 * The text field object which inherits the Battery charge in percent
	 */
	private JTextField txtBatteryCharge;
	/**
	 * The text field object which inherits the total generated energy
	 */
	private JTextField txtTotalGenerated;
	/**
	 * The text field object which inherits the current output
	 */
	private JTextField txtCurrentOutput;
	/**
	 * The text field object which inherits the current energy use
	 */
	private JTextField txtCurrentUse;
	/**
	 * The text field object which inherits the current runtime
	 */
	private JTextField txtRuntime;
	/**
	 * The text field object which inherits the current battery charge
	 */
	private JTextField txtCurrBatteryCharge;
	//panels
	/**
	 * the content pane for solarpanels
	 */
	private JPanel pnlSolarPanel;
	/**
	 * the content pane for batteries
	 */
	private JPanel pnlBatteries;
	/**
	 * the content pane for lamps
	 */
	private JPanel pnlLamp;
	/**
	 * the content pane for hotplates
	 */
	private JPanel pnlHotplate;
	/**
	 * the content pane for radiator
	 */
	private JPanel pnlRadiator;
	//buttons
	/**
	 * the Button for setting the battery charge
	 */
	private JButton btnSetBatteryCharge;
	/**
	 * the start button
	 */
	private JButton btnStart;
	/**
	 * the stop button
	 */
	private JButton btnStop;
	/**
	 * the pause button
	 */
	private JButton btnPause;
	/**
	 * the button for calling the calculator
	 */
	private JButton btnCalculator;
	//combobox
	/**
	 * the wetaher combobox for choosing the current weather
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox cboWeather;
	//slider
	/**
	 * the slider for setting the battery charge
	 */
	private JSlider slrBatteryCharge;
	/**
	 * the ActionListener/Eventhandler for button actions
	 */
	private EventHandler mEventHandler;
	/**
	 * the ActionListener/Eventhandler for the timer
	 */
	private TimerHandler mTimerHandler;
	/**
	 * the Energy handler for the simulator
	 */
	private EnergyHandler mEnergyHandler;
	/**
	 * the timer which ticks every second
	 */
	private Timer mTimer;
	/**
	 * The constructor of the SimulationGUI
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SimulationGUI() {
		setTitle("Simulator");
		//auto generated part
		//used with the GUI Creator because i was to lazy
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 615);
		setMinimumSize(new Dimension(1000,600));
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel pnlController = new JPanel();
		pnlController.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlController.setBounds(10, 18, 314, 357);
		getContentPane().add(pnlController);
		pnlController.setLayout(null);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(10, 289, 64, 23);
		pnlController.add(btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(232, 289, 72, 23);
		pnlController.add(btnStop);
		
		txtAmountBattery = new JTextField();
		txtAmountBattery.setText("1");
		txtAmountBattery.setBounds(258, 11, 46, 20);
		pnlController.add(txtAmountBattery);
		txtAmountBattery.setColumns(10);
		
		JLabel lblAnzahlBatterien = new JLabel("Anzahl Batterien");
		lblAnzahlBatterien.setLabelFor(txtAmountBattery);
		lblAnzahlBatterien.setBounds(10, 14, 165, 14);
		pnlController.add(lblAnzahlBatterien);
		
		JLabel lblAnzahlSolarpanel = new JLabel("Anzahl Solarpanel");
		lblAnzahlSolarpanel.setBounds(10, 143, 165, 14);
		pnlController.add(lblAnzahlSolarpanel);
		
		txtAmountSolarPanel = new JTextField();
		txtAmountSolarPanel.setText("1");
		lblAnzahlSolarpanel.setLabelFor(txtAmountSolarPanel);
		txtAmountSolarPanel.setColumns(10);
		txtAmountSolarPanel.setBounds(258, 140, 46, 20);
		pnlController.add(txtAmountSolarPanel);
		
		JLabel lblAnzahlLampen = new JLabel("Anzahl Lampen");
		lblAnzahlLampen.setBounds(10, 171, 165, 14);
		pnlController.add(lblAnzahlLampen);
		
		txtAmountLamp = new JTextField();
		txtAmountLamp.setText("1");
		lblAnzahlLampen.setLabelFor(txtAmountLamp);
		txtAmountLamp.setColumns(10);
		txtAmountLamp.setBounds(258, 168, 46, 20);
		pnlController.add(txtAmountLamp);
		
		txtAmountHotplate = new JTextField();
		txtAmountHotplate.setText("0");
		txtAmountHotplate.setColumns(10);
		txtAmountHotplate.setBounds(258, 196, 46, 20);
		pnlController.add(txtAmountHotplate);
		
		JLabel lblAnzahlHerdplatten = new JLabel("Anzahl Herdplatten");
		lblAnzahlHerdplatten.setLabelFor(txtAmountHotplate);
		lblAnzahlHerdplatten.setBounds(10, 199, 165, 14);
		pnlController.add(lblAnzahlHerdplatten);
		
		JLabel lblAnzahlHeizungen = new JLabel("Anzahl Heizungen");
		lblAnzahlHeizungen.setBounds(10, 230, 165, 14);
		pnlController.add(lblAnzahlHeizungen);
		
		txtAmountRadiator = new JTextField();
		txtAmountRadiator.setText("0");
		lblAnzahlHeizungen.setLabelFor(txtAmountRadiator);
		txtAmountRadiator.setColumns(10);
		txtAmountRadiator.setBounds(258, 227, 46, 20);
		pnlController.add(txtAmountRadiator);
		
		slrBatteryCharge = new JSlider();
		slrBatteryCharge.setBounds(10, 78, 294, 23);
		pnlController.add(slrBatteryCharge);
		
		txtBatteryCharge = new JTextField();
		txtBatteryCharge.setText("50");
		txtBatteryCharge.setEditable(false);
		txtBatteryCharge.setColumns(10);
		txtBatteryCharge.setBounds(258, 47, 46, 20);
		pnlController.add(txtBatteryCharge);
		
		JLabel lblBatterieLadung = new JLabel("Batterie Ladung");
		lblBatterieLadung.setLabelFor(txtBatteryCharge);
		lblBatterieLadung.setBounds(10, 50, 165, 14);
		pnlController.add(lblBatterieLadung);
		
		cboWeather = new JComboBox();
		cboWeather.setModel(new DefaultComboBoxModel(weatherTyp.values()));
		cboWeather.setBounds(199, 258, 105, 20);
		pnlController.add(cboWeather);
		
		JLabel lblWetter = new JLabel("Wetter");
		lblWetter.setBounds(10, 261, 46, 14);
		pnlController.add(lblWetter);
		
		btnSetBatteryCharge = new JButton("Ladung Setzen");
		btnSetBatteryCharge.setBounds(173, 104, 129, 23);
		pnlController.add(btnSetBatteryCharge);
		
		btnPause = new JButton("Pause");
		btnPause.setEnabled(false);
		btnPause.setBounds(108, 289, 92, 23);
		pnlController.add(btnPause);
		
		btnCalculator = new JButton("Rechner");
		btnCalculator.setBounds(10, 323, 292, 23);
		pnlController.add(btnCalculator);
		
		JPanel pnlSimulation = new JPanel();
		pnlSimulation.setBounds(334, 18, 640, 465);
		getContentPane().add(pnlSimulation);
		pnlSimulation.setLayout(null);
		
		JLabel lblSolarPanels = new JLabel("Solar Panels");
		lblSolarPanels.setBounds(10, 11, 83, 14);
		pnlSimulation.add(lblSolarPanels);
		
		pnlSolarPanel = new JPanel();
		pnlSolarPanel.setBounds(10, 36, 114, 418);
		pnlSimulation.add(pnlSolarPanel);
		pnlSolarPanel.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lbBatteries = new JLabel("Batterien");
		lbBatteries.setBounds(134, 11, 83, 14);
		pnlSimulation.add(lbBatteries);
		
		pnlBatteries = new JPanel();
		pnlBatteries.setBounds(134, 36, 114, 418);
		pnlSimulation.add(pnlBatteries);
		pnlBatteries.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lbLamp = new JLabel("Lampen");
		lbLamp.setBounds(258, 11, 102, 14);
		pnlSimulation.add(lbLamp);
		
		pnlLamp = new JPanel();
		pnlLamp.setBounds(258, 36, 114, 418);
		pnlSimulation.add(pnlLamp);
		pnlLamp.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lbHotplate = new JLabel("Herdplatten");
		lbHotplate.setBounds(382, 11, 102, 14);
		pnlSimulation.add(lbHotplate);
		
		pnlHotplate = new JPanel();
		pnlHotplate.setBounds(382, 36, 114, 418);
		pnlSimulation.add(pnlHotplate);
		pnlHotplate.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lbRadiator = new JLabel("Heizung");
		lbRadiator.setBounds(506, 11, 102, 14);
		pnlSimulation.add(lbRadiator);
		
		pnlRadiator = new JPanel();
		pnlRadiator.setBounds(506, 36, 114, 418);
		pnlSimulation.add(pnlRadiator);
		pnlRadiator.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lblEinstellungen = new JLabel("Einstellungen");
		lblEinstellungen.setBounds(10, 0, 96, 14);
		getContentPane().add(lblEinstellungen);
		
		JLabel lblSimulation = new JLabel("Simulation");
		lblSimulation.setBounds(334, 0, 71, 14);
		getContentPane().add(lblSimulation);
		
		JPanel pnlInformation = new JPanel();
		pnlInformation.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlInformation.setBounds(10, 403, 314, 173);
		getContentPane().add(pnlInformation);
		pnlInformation.setLayout(null);
		
		JLabel lblTotalGenerated = new JLabel("Total generiert (Watt)");
		lblTotalGenerated.setBounds(10, 14, 152, 14);
		pnlInformation.add(lblTotalGenerated);
		
		txtTotalGenerated = new JTextField();
		txtTotalGenerated.setEditable(false);
		txtTotalGenerated.setColumns(10);
		txtTotalGenerated.setBounds(218, 14, 86, 20);
		pnlInformation.add(txtTotalGenerated);
		
		txtCurrentOutput = new JTextField();
		txtCurrentOutput.setEditable(false);
		txtCurrentOutput.setColumns(10);
		txtCurrentOutput.setBounds(218, 42, 86, 20);
		pnlInformation.add(txtCurrentOutput);
		
		JLabel lblCurrentOutput = new JLabel("Aktueller Output (Watt)");
		lblCurrentOutput.setBounds(10, 42, 152, 14);
		pnlInformation.add(lblCurrentOutput);
		
		JLabel lbCurrentUse = new JLabel("Aktueller Verbrauch (Watt)");
		lbCurrentUse.setBounds(10, 70, 152, 14);
		pnlInformation.add(lbCurrentUse);
		
		txtCurrentUse = new JTextField();
		txtCurrentUse.setEditable(false);
		txtCurrentUse.setColumns(10);
		txtCurrentUse.setBounds(218, 70, 86, 20);
		pnlInformation.add(txtCurrentUse);
		
		JLabel lblLaufzeit = new JLabel("Laufzeit (Sekunden)");
		lblLaufzeit.setBounds(10, 129, 152, 14);
		pnlInformation.add(lblLaufzeit);
		
		txtRuntime = new JTextField();
		txtRuntime.setBounds(218, 129, 86, 20);
		pnlInformation.add(txtRuntime);
		txtRuntime.setEditable(false);
		txtRuntime.setColumns(10);
		
		JLabel lblBatteryLadung = new JLabel("Gesamte Battery Ladung (A/h)");
		lblBatteryLadung.setBounds(10, 98, 198, 14);
		pnlInformation.add(lblBatteryLadung);
		
		txtCurrBatteryCharge = new JTextField();
		txtCurrBatteryCharge.setEditable(false);
		txtCurrBatteryCharge.setColumns(10);
		txtCurrBatteryCharge.setBounds(218, 98, 86, 20);
		pnlInformation.add(txtCurrBatteryCharge);
		
		JLabel lblDaten = new JLabel("Daten");
		lblDaten.setBounds(10, 386, 96, 14);
		getContentPane().add(lblDaten);
		
		//end of the auto generated code
		this.mEnergyHandler = new EnergyHandler();
		this.mTimerHandler = new TimerHandler(this, mEnergyHandler);
		this.mEventHandler = new EventHandler(this, mEnergyHandler);
		//Add the ELements to the Eventhandler
		this.mTimer = new Timer(1000, mTimerHandler);
		btnStart.addActionListener(mEventHandler);
		btnStop.addActionListener(mEventHandler);
		btnPause.addActionListener(mEventHandler);
		btnSetBatteryCharge.addActionListener(mEventHandler);
		btnCalculator.addActionListener(mEventHandler);
		cboWeather.addActionListener(mEventHandler);
		slrBatteryCharge.addChangeListener(mEventHandler);
		setVisible(true);
		
	}
	/**
	 * get the text object txtAmountBattery
	 * @return the text object txtAmountBattery
	 */
	public JTextField getTxtAmountBattery() {
		return txtAmountBattery;
	}
	/**
	 * get the text object txtAmountSolarPanel
	 * @return the text object txtAmountSolarPanel
	 */
	public JTextField getTxtAmountSolarPanel() {
		return txtAmountSolarPanel;
	}
	/**
	 * get the text object txtAmountLamp
	 * @return the text object txtAmountLamp
	 */
	public JTextField getTxtAmountLamp() {
		return txtAmountLamp;
	}
	/**
	 * get the text object txtAmountHotplate
	 * @return the text object txtAmountHotplate
	 */
	public JTextField getTxtAmountHotplate() {
		return txtAmountHotplate;
	}
	/**
	 * get the text object txtAmountRadiator
	 * @return the text object txtAmountRadiator
	 */
	public JTextField getTxtAmountRadiator() {
		return txtAmountRadiator;
	}
	/**
	 * get the text object txtBatteryCharge
	 * @return the text object txtBatteryCharge
	 */
	public JTextField getTxtBatteryCharge() {
		return txtBatteryCharge;
	}
	/**
	 * get the text object txtTotalGenerated
	 * @return the text object txtTotalGenerated
	 */
	public JTextField getTxtTotalGenerated(){
		return txtTotalGenerated;
	}
	/**
	 * get the text object txtCurrentOutput
	 * @return the text object txtCurrentOutput
	 */
	public JTextField getTxtCurrentOutput(){
		return txtCurrentOutput;
	}
	/**
	 * get the text object txtCurrentUse
	 * @return the text object txtCurrentUse
	 */
	public JTextField getTxtCurrentUse(){
		return txtCurrentUse;
	}
	/**
	 * get the text object txtRuntime
	 * @return the text object txtRuntime
	 */
	public JTextField getTxtRuntime(){
		return txtRuntime;
	}
	/**
	 * get the text object txtCurrBatteryCharge
	 * @return the text object txtCurrBatteryCharge
	 */
	public JTextField getTxtCurrBatteryCharge(){
		return txtCurrBatteryCharge;
	}
	/**
	 * get the Panel object pnlSolarPanel
	 * @return the Panel object pnlSolarPanel
	 */
	public JPanel getPnlSolarPanel() {
		return pnlSolarPanel;
	}
	/**
	 * get the Panel object pnlBatteries
	 * @return the Panel object pnlBatteries
	 */
	public JPanel getPnlBatteries() {
		return pnlBatteries;
	}
	/**
	 * get the Panel object pnlLamp
	 * @return the Panel object pnlLamp
	 */
	public JPanel getPnlLamp() {
		return pnlLamp;
	}
	/**
	 * get the Panel object pnlHotplate
	 * @return the Panel object pnlHotplate
	 */
	public JPanel getPnlHotplate() {
		return pnlHotplate;
	}
	/**
	 * get the Panel object pnlRadiator
	 * @return the Panel object pnlRadiator
	 */
	public JPanel getPnlRadiator() {
		return pnlRadiator;
	}
	/**
	 * get the instance of the timer object
	 * @return the instance of the timer object
	 */
	public Timer getTimer(){
		return mTimer;
	}
	/**
	 * get the Button object pnlRadiator
	 * @return the Button object pnlRadiator
	 */
	public JButton getBtnSetBatteryCharge() {
		return btnSetBatteryCharge;
	}
	/**
	 * get the Button object btnStart
	 * @return the Button object btnStart
	 */
	public JButton getBtnStart() {
		return btnStart;
	}
	/**
	 * get the Button object btnStop
	 * @return the Button object btnStop
	 */
	public JButton getBtnStop() {
		return btnStop;
	}
	/**
	 * get the Button object btnPause
	 * @return the Button object btnPause
	 */
	public JButton getBtnPause(){
		return btnPause;
	}
	/**
	 * get the Button object btnCalculator
	 * @return the Button object btnCalculator
	 */
	public JButton getBtnCalculator(){
		return btnCalculator;
	}
	/**
	 * get the Combobox object cboWeather
	 * @return the Combobox object cboWeather
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getCboWeather() {
		return cboWeather;
	}
	/**
	 * get the Slider object slrBatteryCharge
	 * @return the Slider object slrBatteryCharge
	 */
	public JSlider getSlrBatteryCharge() {
		return slrBatteryCharge;
	}
}
