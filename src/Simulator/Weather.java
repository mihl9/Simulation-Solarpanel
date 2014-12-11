package Simulator;
/**
 * @created 11.12.2014
 * @author Michael Huber
 * @version 1.0
 * This Class represents the Weather. This class is written in Singleton.
 */
public class Weather {
	/**
	 * save the current instance of the class. This is for the singleton pattern
	 */
	private static Weather instance;
	/**
	 * This is the Enumeration of the Weather Types.
	 */
	public enum weatherTyp{
		nice,
		cloudy,
		rainy,
		night
	}
	/**
	 * This Var saves the Current Weather of the SImulation
	 */
	private weatherTyp mCurrentWeather;
	/**
	 * Constructor of the Weather class.
	 * It sets the default weather
	 */
	private Weather(){
		this.setCurrentWeather(weatherTyp.nice);
	}

	/**
	 * Getter for the current weather.
	 * @return the current weather stat.
	 */
	public weatherTyp getCurrentWeather() {
		return mCurrentWeather;
	}
	/**
	 * Setter for the Weather in the Simulation
	 * @param mCurrentWeather the weather which should be set
	 */
	public void setCurrentWeather(weatherTyp mCurrentWeather) {
		this.mCurrentWeather = mCurrentWeather;
	}
	/**
	 * Singleton function. It checks if already an instance exists. If not the create one
	 * @return the current instance of this class.
	 */
	public static Weather getInstance(){
		if(Weather.instance==null){
			Weather.instance = new Weather();
		}
		return Weather.instance;
	}
}
