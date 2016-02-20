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
		
		FFM = coefficients[0] * Math.exp(coefficients[1]);
		
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

	public static double calcADFM(double FFM, double buildUpIndex)
	{
		double ADFM = 0.0;
		
		ADFM = (0.9 * FFM) + (9.5 * Math.exp(buildUpIndex/-50));
		
		return ADFM;
	}

	public static double calcBUI(double BUO, double precip)
	{
		double BUI = 0.0;
		
		BUI = -50 * (Math.log(1-(-Math.exp(BUO / 50))) * Math.exp(1.175 * (precip - 0.1)));
		
		return BUI;
	}
}
