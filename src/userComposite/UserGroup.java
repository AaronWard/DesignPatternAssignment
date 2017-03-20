package userComposite;

import java.util.*;


/**
 * contains the groups of users
 * @author aaron
 *
 */
public class UserGroup extends UserComponent {

	ArrayList<UserComponent> userComponents = new ArrayList<UserComponent>();
	
	String groupName;
	
	public UserGroup(String groupName){
		this.groupName = groupName;
	}
	
	public String getGroupName(){
		return groupName;
	}
	
	public void add(UserComponent newUserComponent){
		userComponents.add(newUserComponent);
	}
	
	public UserComponent getComponent(int index){
		return userComponents.get(index);
	}
	
	public ArrayList<UserComponent> getAllComponent(){		
		return userComponents;
	}
	
	/**
	 * Iterate through the array list of users
	 */
	public void displayUserInfo(){
		System.out.println(getGroupName());
		
		Iterator<UserComponent> userIterator = userComponents.iterator();
		while (userIterator.hasNext()) {
				UserComponent userInfo = (UserComponent) userIterator.next();
				userInfo.displayUserInfo();
		}
	}
}
