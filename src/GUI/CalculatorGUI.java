package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Simulator.tools.Joule;
import Simulator.tools.Joule.TimeUnit;
/**
 * the class of the Calculator GUI. the Purpose for this GUI is to calculate the remaining time
 * @created 14.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class CalculatorGUI extends JFrame  implements ActionListener{
	/**
	 * Serial ID for GUI objects
	 */
	private static final long serialVersionUID = -4994841696062733976L;
	/**
	 * The text field object which inherits the amount of Batteries
	 */
	private JTextField txtAmountBat;
	/**
	 * The text  field object which inherits the Energy Use
	 */
	private JTextField txtEnergyUse;
	/**
	 * The text  field object which inherits the Runtime for the calculation
	 */
	private JTextField txtRuntime;
	/**
	 * The calculator button
	 */
	private JButton btnCalculate;
	/**
	 * The Battery Capacity in Ampere per Hour
	 */
	final float BATTERY_CAPACITY=18;
	/**
	 * The Voltage which should be used for calculating
	 */
	final float VOLT=12;
	/**
	 * Constructor of the Gui.
	 */
	public CalculatorGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Rechner");
		getContentPane().setLayout(null);
		this.setSize(297, 220);
		this.setResizable(false);
		JPanel pnlData = new JPanel();
		pnlData.setBounds(10, 29, 271, 105);
		getContentPane().add(pnlData);
		pnlData.setLayout(null);
		
		txtAmountBat = new JTextField();
		txtAmountBat.setBounds(191, 8, 70, 20);
		pnlData.add(txtAmountBat);
		txtAmountBat.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Anzahl Batterien (18A/H)");
		lblNewLabel.setBounds(10, 14, 171, 14);
		pnlData.add(lblNewLabel);
		
		JLabel lblEnergieVerbrauch = new JLabel("Energie verbrauch (Watt)");
		lblEnergieVerbrauch.setBounds(10, 45, 171, 14);
		pnlData.add(lblEnergieVerbrauch);
		
		txtEnergyUse = new JTextField();
		txtEnergyUse.setColumns(10);
		txtEnergyUse.setBounds(191, 39, 70, 20);
		pnlData.add(txtEnergyUse);
		
		txtRuntime = new JTextField();
		txtRuntime.setColumns(10);
		txtRuntime.setBounds(191, 67, 70, 20);
		pnlData.add(txtRuntime);
		
		JLabel lblLaufzeit = new JLabel("Laufzeit (In Minuten)");
		lblLaufzeit.setBounds(10, 73, 171, 14);
		pnlData.add(lblLaufzeit);
		
		JLabel lblDaten = new JLabel("Daten");
		lblDaten.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDaten);
		
		btnCalculate = new JButton("Berrechnen");
		btnCalculate.setBounds(10, 141, 115, 23);
		getContentPane().add(btnCalculate);
		
		this.btnCalculate.addActionListener(this);
		this.setVisible(true);
	}
	/**
	 * the Event listener for the Button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//calculate the time
		float runtime=0;
		try {
			if(Integer.parseInt(txtAmountBat.getText().toString())>0){
				if(Integer.parseInt(txtEnergyUse.getText())>0){
					if(Integer.parseInt(txtRuntime.getText())>0){
						
							/*batteryCharge=Joule.convertTimeUnit(BATTERY_CAPACITY*Float.parseFloat(this.txtAmountBat.getText()), TimeUnit.h, TimeUnit.s);
							batteryCharge=Joule.calcPerformance(VOLT, batteryCharge);
							runtime=batteryCharge/Float.parseFloat(this.txtEnergyUse.getText());
							if(runtime>=Joule.convertTimeUnit(Float.parseFloat(this.txtRuntime.getText()), TimeUnit.m, TimeUnit.s)){
								runtime=Joule.convertTimeUnit(Float.parseFloat(this.txtRuntime.getText()), TimeUnit.m, TimeUnit.s) ;
							}*/
							runtime = CalcTime(Integer.parseInt(txtAmountBat.getText().toString()), Float.parseFloat(this.txtRuntime.getText()), Float.parseFloat(this.txtEnergyUse.getText()));
							JOptionPane.showMessageDialog(null,
								    "Die Geräte können für " + Joule.convertTimeUnit(runtime, TimeUnit.s, TimeUnit.m) + " Minuten laufen");
					}
				}
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Fehlerhafte eingabe. Bitte geben Sie eine gültige Zahl ein.","Fehler",JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Calculates the remaining time
	 * @param amountBatteries the amount of batteries
	 * @param runtimeMinute the runtime in Minutes as an float
	 * @param energyUse the current energy use
	 * @return the result as a float value
	 */
	public float CalcTime(int amountBatteries, float runtimeMinute, float energyUse){
		float runtime;
		float batteryCharge;
		if(amountBatteries>0 && runtimeMinute>0 && energyUse>0){
			try{
				batteryCharge=Joule.convertTimeUnit(BATTERY_CAPACITY*amountBatteries, TimeUnit.h, TimeUnit.s);
				batteryCharge=Joule.calcPerformance(VOLT, batteryCharge);
				runtime=batteryCharge/energyUse;
				if(runtime>=Joule.convertTimeUnit(runtimeMinute, TimeUnit.m, TimeUnit.s)){
					runtime=Joule.convertTimeUnit(runtimeMinute, TimeUnit.m, TimeUnit.s) ;
				}
			}catch (Exception e){
				e.printStackTrace();
				runtime = -1;
			}
		}else{
			runtime=-1;
		}
		return runtime;
	}
}
