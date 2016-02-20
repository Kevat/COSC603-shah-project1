package FireDangerSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class CalculateSpreads is used to calculate the various spreads.
 */
public class CalculateSpreads {
	
	/**
	 * Calculates FFM.
	 *
	 * @param dry the dry
	 * @param wet the wet
	 * @return the double
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
	 * Gets the FFM coefficients.
	 *
	 * @param dryWetDiff - Difference between dry and wet bulb readings
	 * @param coefficients - Coefficients to be returned: 0 = A, 1 = B
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
	 * Calc adfm.
	 *
	 * @param FFM the ffm
	 * @param BUO the build up index
	 * @return the double
	 */
	public static double calcADFM(double FFM, double BUO)
	{
		double ADFM = 0.0;
		
		ADFM = (0.9 * FFM) + (9.5 * Math.exp(BUO/-50));
		
		return ADFM;
	}

	/**
	 * Calc BUO.
	 *
	 * @param BUO the BUO
	 * @param precip the precip
	 * @return the double
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
	 * Calc grass spread.
	 *
	 * @param windSpeed the wind speed
	 * @param FFM the ffm
	 * @return the double
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
	 * Calc timber spread.
	 *
	 * @param windSpeed the wind speed
	 * @param ADFM the adfm
	 * @return the double
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
	 * Gets the spread coefficients.
	 *
	 * @param windSpeed the wind speed
	 * @param coefficients the coefficients
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
	 * Calc df.
	 *
	 * @param FFM the ffm
	 * @return the double
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
	 * Calc f load.
	 *
	 * @param timberSpread the timber spread
	 * @param BUO the buo
	 * @return the double
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
	 * Adjust ffm for herb stage.
	 *
	 * @param FFM the ffm
	 * @param herbaceousStage the herbaceous stage
	 * @return the double
	 */
	public static double adjustFFMForHerbStage(double FFM, double herbaceousStage) 
	{
		FFM = FFM + FFM * (herbaceousStage - 1) * 0.05;
		return FFM;
	}
}
