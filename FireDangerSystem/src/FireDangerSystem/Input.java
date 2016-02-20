package FireDangerSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// TODO: Auto-generated Javadoc
/**
 * The Class Input.
 */
public class Input {
	
	/**
	 * Gets the input string.
	 *
	 * @param prompt the prompt
	 * @return the string
	 */
	public static String GetInputString(String prompt)
	{
		String value = "";

		//Reader used to read data entered by user
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));

        //Ask user for prompt
        System.out.println(prompt);
        
        try {
	        //Read user input
	        value = reader.readLine();
        }
        catch (Exception ex)
        {
        	//Log exception with user input
        }
				
		return value;
	}
	
	/**
	 * Gets the input double.
	 *
	 * @param prompt the prompt
	 * @return the double
	 */
	public static double GetInputDouble(String prompt)
	{
		String inputString;
		double inputDouble = 0.0;
		boolean hasError = true;
		
		while (hasError)
		{
			try
			{
				inputString = GetInputString(prompt);
				inputDouble = Double.parseDouble(inputString);
				hasError = false;
			}
			catch (Exception ex)
			{
				System.out.println("Invalid number entered! Please enter a valid number!");
			}
		}
		return inputDouble;
	}
	
	/**
	 * Gets the input boolean.
	 *
	 * @param prompt the prompt
	 * @return the boolean
	 */
	public static Boolean GetInputBoolean(String prompt)
	{
		char inputYN = ' ';
		
		while (inputYN != 'y' && inputYN != 'n')
		{
			String inputString = GetInputString(prompt);
			inputYN = inputString.toLowerCase().charAt(0);
		}
		
		Boolean input = true;
		
		if (inputYN == 'y')
		{
			input = true;
		}
		else if (inputYN == 'n')
		{
			input = false;
		}
		
		return input;
	}
}