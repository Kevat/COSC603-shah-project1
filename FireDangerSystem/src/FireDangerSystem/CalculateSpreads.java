/*
 * 
 */
package FireDangerSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class CalculateSpreads is used to calculate the various spreads.
 */
public class CalculateSpreads {
	
	/**
	 * Calculates Fine Fuel Moisture (FFM).
	 *
	 * @param dry Dry-bulb reading
	 * @param wet Wet-bulb reading
	 * @return Fine Fuel Moisture
	 */
	public static double calcFFM(double dry, double wet)
	{
		double FFM = 0.0;
		double dryWetDiff = dry - wet;
		double[] coefficients = new double[2];
		
		GetFFMCoefficients(dryWetDiff, coefficients);
		
		FFM = coefficients[0] * Math.exp(coefficients[1] * dryWetDiff);
		
		return FFM;
	}

	/**
	 * Gets the FFM coefficients used for calculating FFM.
	 *
	 * @param dryWetDiff Difference between dry and wet bulb readings
	 * @param coefficients Coefficients to be returned: 0 = A, 1 = B
	 */
	private static void GetFFMCoefficients(double dryWetDiff, double [] coefficients) 
	{	
		if (dryWetDiff < 4.5)
		{
			coefficients[0] = 30;
			coefficients[1] = -.1859;
		}
		else if (dryWetDiff < 12.5)
		{
			coefficients[0] = 19.2;
			coefficients[1] = -.0859;
		}
		else if (dryWetDiff < 27.5)
		{
			coefficients[0] = 13.8;
			coefficients[1] = -.0579;
		}
		else if (dryWetDiff >= 27.5)
		{
			coefficients[0] = 22.5;
			coefficients[1] = -.0774;
		}
	}

	/**
	 * Calculates Adjusted Fuel Moisture (ADFM).
	 *
	 * @param FFM Fine Fuel Moisture
	 * @param BUO Build up index
	 * @return Adjusted Fuel Moisture
	 */
	public static double calcADFM(double FFM, double BUO)
	{
		double ADFM = 0.0;
		
		ADFM = (0.9 * FFM) + (9.5 * Math.exp(BUO/-50));
		
		return ADFM;
	}

	/**
	 * Calculates adjustments to the Build Up Index (BUO) based on the level of precipitation.
	 *
	 * @param BUO Previous Build Up Index
	 * @param precip Level or precipitation
	 * @return Adjusted Build Up Index (BUO)
	 */
	public static double calcBUO(double BUO, double precip)
	{
		BUO = -50 * (Math.log(1-(1-Math.exp(-BUO / 50)) * Math.exp(-1.175 * (precip - 0.1))));
		
		if (BUO < 0)
		{
			BUO = 0;
		}
		
		return BUO;
	}
	
	/**
	 * Calculates Grass Spread Index.
	 *
	 * @param windSpeed Wind Speed
	 * @param FFM Fine Fuel Moisture
	 * @return Grass Spread Index
	 */
	public static double calcGrassSpread(double windSpeed, double FFM)
	{
		double grassSpread = 0.0;
		double[] coefficients = new double[2];
		
		GetSpreadCoefficients(windSpeed, coefficients);
		grassSpread = (coefficients[0] * (windSpeed + coefficients[1]) * (Math.pow((33-FFM), 1.65))) - 3;
		
		if (grassSpread > 99)
			grassSpread = 99;
		
		return grassSpread;
	}

	/**
	 * Calculates Timber Spread Index.
	 *
	 * @param windSpeed Wind Speed
	 * @param ADFM Adjusted Fuel Moisture
	 * @return Timber Spread Index
	 */
	public static double calcTimberSpread(double windSpeed, double ADFM)
	{
		double timberSpread = 0.0;
		double[] coefficients = new double[2];
		
		GetSpreadCoefficients(windSpeed, coefficients);
		timberSpread = (coefficients[0] * (windSpeed + coefficients[1]) * (Math.pow((33-ADFM), 1.65))) - 3;

		if (timberSpread > 99)
			timberSpread = 99;
		
		return timberSpread;
	}

	/**
	 * Gets the spread coefficients used to calculate Grass and Timber Spread Indexes.
	 *
	 * @param windSpeed Wind Speed
	 * @param coefficients Coefficients to be returned: 0 = A, 1 = B
	 */
	private static void GetSpreadCoefficients(double windSpeed, double[] coefficients) {
		if (windSpeed < 14)
		{
			coefficients[0] = 0.01312;
			coefficients[1] = 6.0;
		}
		else
		{
			coefficients[0] = 0.009184;
			coefficients[1] = 14.4;
		}
		
	}
	
	
	/**
	 * Calculate Drying Factor (DF).
	 *
	 * @param FFM Fine Fuel Moisture
	 * @return Drying Factor
	 */
	public static double calcDF(double FFM)
	{
		double df = 0.0;
		
		if (FFM > 16)
			df = 0;
		else if (FFM > 10)
			df = 1;
		else if (FFM > 7)
			df = 2;
		else if (FFM > 5)
			df = 3;
		else if (FFM > 4)
			df = 4;
		else if (FFM > 3)
			df = 5;
		else
			df = 7;
		
		return df;
	}
	
	/**
	 * Calculate Fire Load.
	 *
	 * @param timberSpread Timber Spread Index
	 * @param BUO Build Up Index
	 * @return Fire Load
	 */
	public static double calcFLoad(double timberSpread, double BUO)
	{
		double fLoad = 0.0;
		
		fLoad = (1.75 * Math.log10(timberSpread)) + (0.32 * Math.log10(BUO)) - 1.64;
		
		if (fLoad > 0)
			fLoad = Math.pow(10, fLoad);
		else
			fLoad = 0;
		 
				
		return fLoad;
	}

	/**
	 * Adjust FFM based on the herbaceous stage of vegetation.
	 *
	 * @param FFM Fine Fuel Moisture
	 * @param herbaceousStage Herbaceous stage of vegetation (1=CURED 2=TRANSITION 3=GREEN)
	 * @return Fine Fuel Moisture
	 */
	public static double adjustFFMForHerbStage(double FFM, double herbaceousStage) 
	{
		FFM = FFM + FFM * (herbaceousStage - 1) * 0.05;
		return FFM;
	}
}
