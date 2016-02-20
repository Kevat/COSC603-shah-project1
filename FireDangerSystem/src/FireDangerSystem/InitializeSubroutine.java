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
		double prevBUO; //Yesterday's buildup
		double herbaceousStage;
		
		
		//Variables to hold output data
		double DF; // Drying factor
		double FFM; //Fine fuel moisture
		double ADFM; //Adjusted fuel moisture
		double grassSpread; //Grass spread
		double timberSpread; //Timber spread
		double fLoad; //Fire load rating
		double BUO; //Build up index
		
		dryBulbReading = Input.GetInputDouble("Please input the dry bulb reading:");
		wetBulbReading = Input.GetInputDouble("Please input the wet bulb reading:");
		snowOnGround = Input.GetInputBoolean("Is there snow on the ground? (Y/N):");
		precip = Input.GetInputDouble("Please input the preceding 24-hour precipitation:");
		windSpeed = Input.GetInputDouble("Please input the wind speed:");
		prevBUO = Input.GetInputDouble("Please input yesterday's build up index:");
		herbaceousStage = Input.GetInputDouble("Please input the current herbaceous stage of vegetation (1=CURED 2=TRANSITION 3=GREAAN):");
		
		FFM = CalculateSpreads.calcFFM(dryBulbReading, wetBulbReading);
		DF = CalculateSpreads.calcDF(FFM);
		//Adjust FFM
		
		BUO = CalculateSpreads.calcBUO(prevBUO, precip);
		
		//Adjust BUO for rain (BUO is negative)
		
		//Increase BUO by drying factor
		ADFM = CalculateSpreads.calcADFM(FFM, BUO);
		
		
		//Set indexes to 1 if ADFM AND FFM are greater than 30 
		//(NOTE: discrepancy between flow chart (33%) and code (30%)
		
		//Calculate indexes if ADFM or FFM below 30
		grassSpread = CalculateSpreads.calcGrassSpread(windSpeed, FFM);
		timberSpread = CalculateSpreads.calcTimberSpread(windSpeed, ADFM);
		
		
		//Calculate FLoad
		fLoad = CalculateSpreads.calcFLoad(timberSpread, BUO);
		
        System.out.println("The first name is: " + dryBulbReading);
        System.out.println("The last name is: " + wetBulbReading);
	}

}