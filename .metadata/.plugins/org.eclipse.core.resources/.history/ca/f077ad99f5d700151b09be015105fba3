package FireDangerSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class CalculateSpreads.
 */
public class CalculateSpreads {
	
	/**
	 * Calc ffm.
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
	 * Gets the ffm coefficients.
	 *
	 * @param dryWetDiff the dry wet diff
	 * @param coefficients the coefficients
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
}
