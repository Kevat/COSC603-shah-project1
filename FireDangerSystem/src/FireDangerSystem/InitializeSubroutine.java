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
		double precip;//Preceding precipitation
		double windSpeed;
		double BUO; //Yesterday's buildup
		double herbaceousStage;
		
		
		//Variables to hold output data
		double DF; // Drying factor
		double FFM; //Fine fuel moisture
		double ADFM; //Adjusted fuel moisture
		double grassSpreadIndex; //Grass spread
		double timberSpreadIndex; //Timber spread
		double fireLoadRating; //Fire load rating
		double BUI; //Build up index
		
		dryBulbReading = Input.GetInputDouble("Please input the dry bulb reading:");
		wetBulbReading = Input.GetInputDouble("Please input the wet bulb reading:");
		snowOnGround = Input.GetInputBoolean("Is there snow on the ground? (Y/N):");
		precip = Input.GetInputDouble("Please input the preceding 24-hour precipitation:");
		windSpeed = Input.GetInputDouble("Please input the wind speed:");
		BUO = Input.GetInputDouble("Please input yesterday's build up index:");
		herbaceousStage = Input.GetInputDouble("Please input the current herbaceous stage of vegetation (1=CURED 2=TRANSITION 3=GREAAN):");
		
		FFM = CalculateSpreads.calcFFM(dryBulbReading, wetBulbReading);
		BUI = CalculateSpreads.calcBUI(BUO, precip);
		ADFM = CalculateSpreads.calcADFM(FFM, BUI);
		
		
        System.out.println("The first name is: " + dryBulbReading);
        System.out.println("The last name is: " + wetBulbReading);
	}

}