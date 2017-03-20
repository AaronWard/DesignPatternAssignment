package userComposite;

import java.util.ArrayList;

public class UserListMaker {

	UserComponent userList;
	ArrayList<UserComponent> arr;
	
	public UserListMaker(UserComponent newUserList){
			userList = newUserList;
	}
	public void  getUserList(){
//		userList.displayUserInfo();
		arr = userList.getAllComponent();
	}	
	
	public ArrayList<UserComponent> getAllUsers(){
		return arr;
	}
}
