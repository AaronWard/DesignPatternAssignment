package userComposite;

import java.util.ArrayList;

public class UserListMaker {

	UserComponent userList;
	//Store list of users in an ArrayList
	ArrayList<UserComponent> arr;
	
	public UserListMaker(UserComponent newUserList){
			userList = newUserList;
	}
	//get the list of users and return
	public ArrayList<UserComponent> getAllUsers(){
		arr = userList.getAllComponent();
		return arr;
	}
}
