package userComposite;

public class User extends UserComponent{

		String userName;
		String countyName;
		String favouriteCake;
		
		public User(String userName, String countyName){
			this.userName = userName;
			this.countyName = countyName;
		}
		
//		
//		public User(String userName, String countyName, String favouriteCake){
//			this.userName = userName;
//			this.countyName = countyName;
//			this.favouriteCake = favouriteCake;
//		}
	
		public String getUserName(){
			return userName;
		}
		
		public String getCountyName(){
			return countyName;
		}
		
		public String getFavouriteCake() {
			return favouriteCake;
		}
		
		public void displayUserInfo(){
			System.out.println("USER INFO: " + getUserName() + " - " + getCountyName());
		}		
}
