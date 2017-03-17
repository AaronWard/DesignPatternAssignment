package Singleton;
/**
 * This singleton limits the creation of the Login object. 
 * The static method ensures that there is only one instance of 
 * of the loginInstance thoughout the entire program. This prevents multiple
 * logins of users
 * @author aaron
 *
 */
public class LoginSingleton {
	
	private static LoginSingleton loginInstance = null;
	private static String id = null;
	
	/**
	 * Empty Constructor
	 */
	private LoginSingleton(){}
	/**
	 * Singleton get Instance method that checkes if the instance
	 * has already been created
	 * @param id
	 * @return
	 */
	public static LoginSingleton getLoginInstance(String id){
		//Set the ID of the user	
		LoginSingleton.id = id;
			
			if(loginInstance == null){
					loginInstance = new LoginSingleton();
			}
			return loginInstance;
	}
	
	/**
	 * returns the instance of the ID set when
	 * logged in
	 * @return String
	 */
	public static String getId(){
		return id;	
	}
	
	/**
	 * Sets the loginInstance and ID back to null
	 * when user wishes to log out
	 */
	public static void logOut(){
		loginInstance = null;
		id = null;
	}
}
 