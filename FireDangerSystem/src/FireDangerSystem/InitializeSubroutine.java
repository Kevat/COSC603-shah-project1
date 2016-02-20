/*
 * 
 */
package FireDangerSystem;

/**
 * @author Kevat Shah
 * @Version 1.0
 */
/**
 * @author Kevat Shah
 * @Version 1.0
 */
public class InitializeSubroutine {
	/**
	 * The method initializes the fire danger system 
	 * and serves as the main/start method for the system
	 *
	 * @param args - CLI arguments passed in to the system
	 */
	public static void main(String[] args) {
		
		//Variables to hold input data
		double dryBulbReading;
		double wetBulbReading;
		Boolean snowOnGround;
		double precedingPrecipitation;
		double windSpeed;
		double yesterdaysBuildUp;
		double herbaceousStage;
		
		
		//Variables to hold output data
		double dryingFactor;
		double fineFuelMoisture;
		double adjustedFuelMoisture;
		double grassSpreadIndex;
		double timberSpreadIndex;
		double fireLoadRating;
		double buildUpIndex;
		
		dryBulbReading = Input.GetInputDouble("Please input the dry bulb reading:");
		wetBulbReading = Input.GetInputDouble("Please input the wet bulb reading:");
		snowOnGround = Input.GetInputBoolean("Is there snow on the ground? (Y/N):");
		precedingPrecipitation = Input.GetInputDouble("Please input the preceding 24-hour precipitation:");
		windSpeed = Input.GetInputDouble("Please input the wind speed:");
		yesterdaysBuildUp = Input.GetInputDouble("Please input yesterday's build up index:");
		herbaceousStage = Input.GetInputDouble("Please input the current herbaceous stage of vegetation (1=CURED 2=TRANSITION 3=GREAAN):");
				
        System.out.println("The first name is: " + dryBulbReading);
        System.out.println("The last name is: " + wetBulbReading);
	}

}