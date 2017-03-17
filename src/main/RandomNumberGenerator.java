package main;

import java.util.Random;

/**
 * this class creates a random number for users to recieve user ID's.
 * this number is stored into the database
 * @author aaron
 *
 */
public class RandomNumberGenerator {
		
	public String generator(){
		Random random = new Random();
		String idString ="";
		int [] idArray = new int [3];
		
		/** Loop 3 times and create a string for the ID**/
		for(int i = 0; i < idArray.length; i++)
		{
			//Populate the array with a random number
			idArray[i] = random.nextInt(9)+1;
			
			//Concatenate the indices in the array to form
			//a string for the ID
			idString += idArray[i] +"";
		}
		return idString;
	}
}
