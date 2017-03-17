package singleton;
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
	private static String name = "";
	
	/**
	 * Empty Constructor
	 */
	private LoginSingleton(){}
	/**
	 * singleton get Instance method that checkes if the instance
	 * has already been created
	 * @param id
	 * @return
	 */
	public static LoginSingleton getLoginInstance(String id, String name){
		//Set the ID of the user	
		LoginSingleton.id = id;
		LoginSingleton.name = name;
			
		//Check if an instance has already been created
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
	
	public static String getName(){
		return name;
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
 