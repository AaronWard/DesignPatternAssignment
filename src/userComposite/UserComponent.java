package userComposite;

import java.util.ArrayList;

/**
 * this class will act as an interface for users and user groups (Counties)
 * @author aaron
 *
 */
public abstract class UserComponent {
	public void add(UserComponent newUserComponent){
		throw new UnsupportedOperationException();
	}
	
	public UserComponent getComponent(int index){
		throw new UnsupportedOperationException();
	}
	
	public String getUserName(){
		throw new UnsupportedOperationException();
	}
		
	public String getCountyName(){
		throw new UnsupportedOperationException();
	}
	
	public String getUserID(){
		throw new UnsupportedOperationException();
	}
	
	public ArrayList<UserComponent>getAllComponent(){
		throw new UnsupportedOperationException();
	}
	
	public void displayUserInfo(){
		throw new UnsupportedOperationException();
	}
}
