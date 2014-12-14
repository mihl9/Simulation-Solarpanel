package Simulator.tools;
/**
 * Class for calculating the energy use and other important formulas.
 * @created 28.11.2014
 * @author Michael Huber
 * @version 1.0
 */
public class Joule {
	
	/**
	 * @name: TimeUnit
	 * @purpose: A Public Enumeration for the defintion of the several time formats
	 * @contains: h=Hour, m=Minute, s=Second
	 */
	public enum TimeUnit{
		h,
		m,
		s
	}
	/**
	 * Convert the given Value (KWH) into joule
	 * @param kWHour the value which should be converted
	 * @return the value in Joule
	 */
	public static float convertKiloWattHourToJoule(float kWHour){
		float result;
		result = kWHour*3600000;
		return result;
	}
	/**
	 * Convert the given Value into Kilo Watt Hours
	 * @param The Value wich should be converted
	 * @return the result (KWH)
	 */
	public static float convertJouleToKWattHour(float joule){
		float result;
		result = joule*3600000;
		return result;
	}
	/**
	 * convert a given Value from the Given timeUnit to the Other
	 * for Example 60Watt/H to Watt/S = 216000 Watt/s
	 * @param value the given start Value (f.e. Watt/H)
	 * @param start the start time Type (h,m,s)
	 * @param target the end time Type (h,m,s)
	 * @return the recalculated Value with the given time
	 */
	public static float convertTimeUnit(float value, TimeUnit start, TimeUnit target ){
		float result=0;
		if(value!=0){
			switch (start){
				case h:
					result = value*3600;
					break;
				case m:
					result = value*60;
					break;
				case s:
					result = value;
					break;
				default:
					result=0;
					break;
			}
			
			switch (target) {
				case h:
					result = result/3600;
					break;
				case m:
					result = result/60;
					break;
				case s:
					//nothing todo
					break;
				default:
					result=0;
					break;
			}
		}else{
			result=0;
		}
		return result;
	}
	/**
	 * Calculates the Result based on the formula I=U/R and returns the amperage
	 * @param voltage current voltage (Volt)
	 * @param resistance current resistance (Ohm)
	 * @return the calculated amperage (Ampere)
	 */
	public static float calcAmperage(float voltage, float resistance){
		float result=0;
		try {
			result=voltage/resistance;
		} catch (Exception e) {
			// if an error occurs the return only 0
			result=0;
		}
		return result;
	}
	
	/**
	 * Calculates the Result based on the formula I=P/V and returns the amperage
	 * @param power current power (Watt)
	 * @param voltage current voltage (Volt)
	 * @return the calculated amperage (Ampere)
	 */
	public static float calcAmperageWithWatt(float power, float voltage){
		float result=0;
		try {
			result=power/voltage;
		} catch (Exception e) {
			// if an error occurs the return only 0
			result=0;
		}
		return result;
	}
	/**
	 * Calculates the Result based on the formula U=R*I and returns the voltage
	 * @param resistance current resistance (Ohm)
	 * @param amperage current amperage (Ampere)
	 * @return the calculated voltage (Volt)
	 */
	public static float calcVoltage(float resistance, float amperage ){
		float result=0;
		try {
			result=resistance*amperage;
		} catch (Exception e) {
			// if an error occurs the return only 0
			result=0;
		}
		return result;
	}
	/**
	 * Calculates the Result based on the formula R=U/I and returns the resistance
	 * @param voltage current voltage (Volt)
	 * @param amperage current amperage (Ampere)
	 * @return the calculated resistance (Ohm)
	 */
	public static float calcResistance(float voltage, float amperage){
		float result=0;
		try {
			result=voltage/amperage;
		} catch (Exception e) {
			// if an error occurs the return only 0
			result=0;
		}
		return result;
	}
	/**
	 * Calculates the Result based on the formula P=U*I and returns the performance
	 * @param voltage current voltage (Volt)
	 * @param amperage current amperage (Ampere)
	 * @return the calculated performance (Watt)
	 */
	public static float calcPerformance(float voltage, float amperage){
		float result=0;
		try {
			result=voltage*amperage;
		} catch (Exception e) {
			// if an error occurs the return only 0
			result=0;
		}
		return result;
	}
}
