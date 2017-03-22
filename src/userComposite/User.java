package userComposite;

public class User extends UserComponent{

		String userName;
		String countyName;
		String userID;
		
		public User(String userName, String countyName, String userID){
			this.userName = userName;
			this.countyName = countyName;
			this.userID = userID;
		}
	
		public String getUserName(){
			return userName;
		}
		
		public String getCountyName(){
			return countyName;
		}
		
		public String getUserID(){
			return userID;
		}
		
		public void displayUserInfo(){
			System.out.println("USER INFO: " + getUserName() + " - " + getCountyName());
		}		
}
