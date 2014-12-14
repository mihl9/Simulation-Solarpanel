package Simulator.devices.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * The Setting Dialog for the device
 * @created 14.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class DevicesSettingsDialog extends JFrame implements ActionListener,ChangeListener {

	/**
	 * The Serial ID for GUI objects
	 */
	private static final long serialVersionUID = -6058341662473774453L;
	/**
	 * The text field object which inherits the current level of the device
	 */
	private JTextField txtLevel;
	/**
	 * The text field object which inherits the current on off interval of the device
	 */
	private JTextField txtIntervall;
	/**
	 * The Slider object for setting the current level
	 */
	private JSlider slrLevel;
	/**
	 * The cancel button
	 */
	private JButton btnCancel;
	/**
	 * The save button
	 */
	private JButton btnSave;
	/**
	 * the ActionListener instance for calling the events
	 */
	private ActionListener listener;
	/**
	 * The constructor of this class
	 * @param listener the instance of the object which should be triggered via the event
	 */
	public DevicesSettingsDialog(ActionListener listener){
		this.listener = listener;
		setTitle("Einstellungen");
		getContentPane().setLayout(null);
		this.setSize(300, 270);
		this.setMaximumSize(new Dimension(326,270));
		this.setMinimumSize(new Dimension(326,270));
		this.setResizable(false);
		btnCancel = new JButton("Abbrechen");
		btnCancel.setBounds(164, 199, 113, 23);
		getContentPane().add(btnCancel);
		
		btnSave = new JButton("Anwenden");
		btnSave.setBounds(38, 199, 116, 23);
		getContentPane().add(btnSave);
		
		JLabel lblNewLabel = new JLabel("Einstellungen");
		lblNewLabel.setBounds(23, 11, 127, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel pnlSettings = new JPanel();
		pnlSettings.setBounds(23, 36, 254, 127);
		getContentPane().add(pnlSettings);
		pnlSettings.setLayout(null);
		
		slrLevel = new JSlider();
		slrLevel.setMaximum(3);
		slrLevel.setMinimum(1);
		slrLevel.setBounds(10, 42, 234, 23);
		pnlSettings.add(slrLevel);
		
		txtLevel = new JTextField();
		txtLevel.setEditable(false);
		txtLevel.setBounds(158, 11, 86, 20);
		pnlSettings.add(txtLevel);
		txtLevel.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Stufe");
		lblNewLabel_1.setBounds(10, 17, 46, 14);
		pnlSettings.add(lblNewLabel_1);
		
		txtIntervall = new JTextField();
		txtIntervall.setBounds(158, 76, 86, 20);
		pnlSettings.add(txtIntervall);
		txtIntervall.setColumns(10);
		
		JLabel lblTimer = new JLabel("Timer (in Sekunden)");
		lblTimer.setBounds(10, 79, 138, 14);
		pnlSettings.add(lblTimer);
		//add the event handler
		btnCancel.addActionListener(this);
		btnSave.addActionListener(this);
		slrLevel.addChangeListener(this);
		this.setCurrentLevel(0);
		this.setVisible(false);
	}
	/**
	 * set the Maximum level for the slider
	 * @param level the maximum level
	 */
	public void setMaxLevel(int level){
		this.slrLevel.setMaximum(level);
	}
	/**
	 * get the maximum level
	 * @return the maximum level
	 */
	public int getMaxLeve() {
		return this.slrLevel.getMaximum();

	}
	/**
	 * get the current level set in the dialog
	 * @return the current level
	 */
	public int getCurrentLevel(){
		return this.slrLevel.getValue();
	}
	/**
	 * set the current level in the Setting Dialog
	 * @param level the level which should be set
	 */
	public void setCurrentLevel(int level) {
		this.slrLevel.setValue(level);
	}
	/**
	 * get the current interval
	 * @return the current interval
	 */
	public int getIntervall(){
		return Integer.parseInt(this.txtIntervall.getText());
	}
	/**
	 * set the interval in the settings dialog
	 * @param intervall the current interval
	 */
	public void setIntervall(int intervall){
		this.txtIntervall.setText(""+intervall);
	}
	/**
	 * this method is triggered if any changes is made in the slider. It sets the the current level
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		this.txtLevel.setText(Integer.toString(slrLevel.getValue()));
	}
	/**
	 * this method is triggered if any button is pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(this.btnCancel.getText())){
			listener.actionPerformed(new ActionEvent(this, 1, "Cancel"));
		}
		
		if(e.getActionCommand().equals(this.btnSave.getText())){
			listener.actionPerformed(new ActionEvent(this, 2, "Save"));
		}
		this.dispose();
	}
}
