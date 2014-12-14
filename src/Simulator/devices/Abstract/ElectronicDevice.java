package Simulator.devices.Abstract;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Simulator.devices.dialog.DevicesSettingsDialog;
import Simulator.devices.interfaces.DeviceInterface;
import Simulator.listeners.DeviceListener;


/**
 * Abstract class of an Electronic device. It inherits the basic functions and properties
 * @created 10.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public abstract class ElectronicDevice extends GuiPanelDevice implements ActionListener, DeviceInterface {
	/**
	 * Serial ID for GUI Objects
	 */
	private static final long serialVersionUID = -1857129969385914248L;
	/**
	 * The Max running level of the device
	 */
	protected final int MAX_LEVEL=3;
	/**
	 * Current running Level
	 */
	protected int mLevel=0;
	/**
	 * The interval in which the device should turn on and off. 0 means unlimited runtime.
	 * The Value should be in Milliseconds
	 */
	protected int mOnOffInterval=0;
	/**
	 * the timer object. which is controlled by the OnOffIntervall value
	 */
	private int mTimer=1;
	/**
	 * Index of the object Battery. It counts up everytime a object is created
	 */
	private static int ID = 0;
	/**
	 * Saves the ID of this object
	 */
	private int mID;
	/**
	 * Inherits the value of the current Power which is used from the device
	 */
	private float mWattPower=50;
	/**
	 * On/Off switch of the Device
	 */
	private boolean mDeviceRunning=true;
	/**
	 * indicates if the object has a setting page or not
	 */
	private boolean mHasSettingsPage=false;
	/**
	 * listener class. For calling the EVents
	 */
	private DeviceListener listener;
	/**
	 * Instance of the On/Off Button
	 */
	private JButton btnOnOff;
	/**
	 * the Setting Page Button
	 */
	private JButton btnSetting;
	/**
	 * Inherits the current instance of the Setting Form
	 */
	private DevicesSettingsDialog frmSetting;
	/**
	 * Constructor of this class
	 * @param listener reference of the DeviceListener. for handling the Events
	 * @param watt Value which defines the Max energy use
	 * @param iconPath the Path to the Image file which should be loaded
	 * @param hasSettingPage indicates if the object has a setting page
	 */
	public ElectronicDevice(DeviceListener listener, float watt, String iconPath, boolean hasSettingPage){
		super(iconPath);
		this.setHasSettingsPage(hasSettingPage);
		this.createButtons();
		if(listener!=null){
			this.listener = listener;
			this.setDeviceRunning(true);
			this.mID = ElectronicDevice.ID;
			ElectronicDevice.ID++;
			this.setWattPower(watt);
			this.mTimer = 0;
			//this.mTimer = new Timer(0, this);
			//this.mTimer.setActionCommand("Switch");
			listener.addDevice(this);
		}
	}
	/**
	 * The overloaded Constructor of this class
	 * @param listener reference of the DeviceListener. for handling the Events
	 * @param watt Value which defines the Max energy use
	 * @param iconPath the Path to the Image file which should be loaded
	 */
	public ElectronicDevice(DeviceListener listener, float watt, String iconPath){
		this(listener,watt,iconPath,false);
	}
	/**
	 * get the current level 
	 * @return the current level
	 */
	public abstract int getLevel();
	/**
	 * Set the level of the device
	 * @param level the device level
	 */
	public abstract void setLevel(int level);
	/**
	 * set the interval of the changing on off state
	 * @param intervall in Milliseconds
	 */
	public void setOnOffInterval(int interval){
		if(interval<0){
			interval=0;
		}
		this.mOnOffInterval = interval;
		/*if(this.mOnOffInterval==0){
			this.mTimer.stop();
		}else{
			this.mTimer.setDelay(interval);
			if(this.mTimer.isRunning()){
				this.mTimer.restart();
			}else{
				this.mTimer.start();
			}
		}*/
		//this.mOnOffInterval = 0;
	}
	/**
	 * Step the counter one step up and reacts if reached the limit.
	 */
	public void doTimerTick(){
		if(this.mOnOffInterval!=0){
			if(this.mTimer<this.getOnOffInterval()){
				this.mTimer++;
			}else{
				this.mTimer=1;
				this.actionPerformed(new ActionEvent(this, 3, "Switch"));
			}
		}
	}
	/**
	 * get the interval in which the device should be set on and off
	 * @return the On Off interval
	 */
	public int getOnOffInterval(){
		return this.mOnOffInterval;
	}
	/**
	 * This method cleans all settings before it is cleaned by the garbage collector
	 */
	protected void finalize () throws Throwable {
	    this.DisconnectFromEnergyHandler();
		super.finalize();
	}
	/**
	 * Remove the Electronic device from the Collection
	 */
	public void DisconnectFromEnergyHandler(){
		this.listener.removeDevice(this);
		ElectronicDevice.ID--;
	}
	
	/**
	 * Get the current Energy use of this object
	 * @return the energy use. (Watt)
	 */
	public float getWattPower() {
		float result=0;
		String toolTipText;
		if(this.isDeviceRunning()){
			result = mWattPower;
		}else{
			result = 0;
		}
		toolTipText = "<html>"+result +" Watt";
		if(this.getLevel()>0){
			toolTipText+="<br/>Level: "+ this.getLevel();
		}
		this.setToolTipText(toolTipText);
		return result;
	}
	/**
	 * Set the energy use of this object
	 * @param wattPower the new energy use
	 */
	protected void setWattPower(float wattPower) {
		if(wattPower>0){
			this.mWattPower = wattPower;
		}else{
			//make no changes
		}
		
	}
	/**
	 * check if the Device is running
	 * @return the boolean value if the device is running
	 */
	public boolean isDeviceRunning() {
		return mDeviceRunning;
	}
	/**
	 * set the Device status
	 * @param DeviceRunning the value which defines if the device is running or not
	 */
	public void setDeviceRunning(boolean DeviceRunning) {
		this.mDeviceRunning = DeviceRunning;
		if(DeviceRunning){
			btnOnOff.setBackground(Color.green);
		}else{
			btnOnOff.setBackground(Color.red);
		}
	}
	/**
	 * Set the Value of the Indicator if the object has a settingspage or not
	 * @param hasPagethe value which should be set
	 */
	protected void setHasSettingsPage(boolean hasPage){
		this.mHasSettingsPage = hasPage;
	}
	/**
	 * Get the indicator if the Setting  page could be displayed
	 * @return the boolean value
	 */
	protected boolean getHasSettingsPage(){
		return this.mHasSettingsPage;
	}
	/**
	 * get the ID of the Device
	 * @return the current ID
	 */
	public int getID() {
		return mID;
	}
	/**
	 * Create the the necessary buttons in the GUI object and sets the Action Listener
	 */
	private void createButtons(){
		this.setLayout(null);
		String btnText="";
		btnOnOff = new JButton(btnText);
		if(this.isDeviceRunning()){
			btnOnOff.setBackground(Color.green);
		}else{
			btnOnOff.setBackground(Color.red);
		}
		btnOnOff.setBounds(0, 0, 20, 20);
		btnOnOff.setActionCommand("OnOff");
		this.add(btnOnOff);
		btnOnOff.addActionListener(this);
		if(this.getHasSettingsPage()){
			btnSetting = new JButton("");
			btnSetting.setBounds(this.getWidth()-20, 0, 20, 20);
			btnSetting.setActionCommand("Setting");
			btnSetting.addActionListener(this);
			this.add(btnSetting);
		}
	}
	/**
	 * Action listener for the On Off button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().toString()=="OnOff"){
			this.setDeviceRunning(!this.isDeviceRunning());
		}
		
		if(e.getActionCommand().toString()=="Setting"){
			this.frmSetting = new DevicesSettingsDialog(this);
			this.frmSetting.setMaxLevel(MAX_LEVEL);
			this.frmSetting.setCurrentLevel(mLevel);
			this.frmSetting.setIntervall(mOnOffInterval);
			this.frmSetting.setVisible(true);
		}
		
		if(e.getActionCommand().toString()=="Save"){
			this.setLevel( this.frmSetting.getCurrentLevel());
			this.setOnOffInterval(this.frmSetting.getIntervall());
			this.frmSetting = null;
		}
		
		if(e.getActionCommand().toString()=="Cancel"){
			this.frmSetting = null;
		}
		
		if(e.getActionCommand().toString()=="Switch"){
			this.setDeviceRunning(!this.isDeviceRunning());
		}
	}
	
	
}
