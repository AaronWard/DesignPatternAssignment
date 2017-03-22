package userComposite;

import java.util.*;


/**
 * contains the groups of users
 * @author aaron
 *
 */
public class UserGroup extends UserComponent {
	
	ArrayList userComponents = new ArrayList();	
	String groupName;
	
	/**
	 * set the group name (dublin/cork/galway)
	 * @param groupName
	 */
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
		return(UserComponent) userComponents.get(index);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserComponent> getAllComponent(){		
		return (ArrayList<UserComponent>) userComponents;
	}
	
	/**
	 * Iterate through the array list of users
	 */
	public void displayUserInfo(){
		System.out.println(getGroupName() + "\n");
		
		//Loop through the users
		Iterator userIterator = userComponents.iterator();
		while (userIterator.hasNext()) {
				UserComponent userInfo = (UserComponent) userIterator.next();
				userInfo.displayUserInfo();
		}
	}
}
