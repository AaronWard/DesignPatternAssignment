package panelsPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.MainDriver;
import panelFactory.PanelFactory;
import userComposite.User;
import userComposite.UserComponent;
import userComposite.UserGroup;
import userComposite.UserListMaker;

public class DisplayUsersPanel extends PanelFactory{
	JPanel mainUsersPanel;
	JPanel leftPanel, rightPanel;
	JPanel innerLeftPanel, innerRightPanel;
	JPanel detailsPanel;
	
	//Labels to display users info
	JLabel userNameLabel;
	JLabel userCountyLabel;
	JLabel userIDLabel;
	
	//Variables used for updating GUI
	String userName; 
	String userCounty;
	String usersFavrouriteCake;
	String id;
	
//	int userCount;
	JList<String> userList;
	JScrollPane scrollPane;
	
	//Store users in an array 
	User [] usersArrayDublin, usersArrayCork, usersArrayGalway;
	//
	String currentUsers = "Dublin";
	
	//Used to count the amount of rows in the counties SQl column
	int dublinCount, corkCount, galwayCount = 0;
	
	
	public JPanel getPanel() {
		mainUsersPanel = new JPanel();
		
		mainUsersPanel.setLayout(new GridLayout(1,2));
		leftPanel = new JPanel();
		rightPanel= new JPanel(new GridLayout(1, 1));
		innerLeftPanel = new JPanel(new BorderLayout());
		innerRightPanel = new JPanel(new BorderLayout());
		
		leftPanel.setBackground(new Color(51,51,51));
		rightPanel.setBackground(new Color(51, 51, 51));
		
		innerLeftPanel.setBackground(MainDriver.northBackground);
		innerRightPanel.setBackground(MainDriver.northBackground);

		innerLeftPanel.setPreferredSize(new Dimension(500, 500));
		innerRightPanel.setPreferredSize(new Dimension(500, 500));
		
		innerLeftPanel.setBorder(BorderFactory.createEmptyBorder (0,0,0, 0));
		
		/** Left Panel **/
		userList= new JList<String>(getDublinListModel());	
		userList.addListSelectionListener(new UserListener());

		userList.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		userList.setBackground(MainDriver.northBackground);
		userList.setForeground(Color.white);
		
        scrollPane = new JScrollPane(userList);	
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        
        JButton dublinButton = new JButton("Dublin");
        JButton corkButton = new JButton("Cork");
        JButton galwayButton = new JButton("Galway");
        
        
        dublinButton.addActionListener(new DublinListener());
        corkButton.addActionListener(new CorkListener());
        galwayButton.addActionListener(new GalwayListener());
        
        dublinButton.setBackground(Color.white);
        corkButton.setBackground(Color.white);
        galwayButton.setBackground(Color.white);

        buttonPanel.add(dublinButton);
        buttonPanel.add(corkButton);
        buttonPanel.add(galwayButton);

        
        innerLeftPanel.add(buttonPanel, BorderLayout.NORTH);
		innerLeftPanel.add(scrollPane);

		
		/** Right Panel **/
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(MainDriver.northBackground);
		
		JLabel detailsLabel = new JLabel("User Details");
		detailsLabel.setFont(new Font("Century Gothic", Font.ITALIC, 24));
		detailsLabel.setForeground(Color.WHITE);
		detailsLabel.setBackground(MainDriver.northBackground);
		
		titlePanel.add(detailsLabel);
		
		detailsPanel = new JPanel(new GridLayout(6,1));		
		userNameLabel = new JLabel("Name: ");
		userCountyLabel = new JLabel("County: ");
		userIDLabel = new JLabel("User ID: ");
		
		userNameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 26));
		userCountyLabel.setFont(new Font("Century Gothic", Font.PLAIN, 26));
		userIDLabel.setFont(new Font("Century Gothic", Font.PLAIN, 26));

		userNameLabel.setForeground(Color.WHITE);
		userCountyLabel.setForeground(Color.WHITE);
		userIDLabel.setForeground(Color.WHITE);
		
		detailsPanel.add(titlePanel);
		detailsPanel.add(userNameLabel);
		detailsPanel.add(userCountyLabel);
		detailsPanel.add(userIDLabel);
		
		detailsPanel.setBackground(MainDriver.northBackground);
		detailsPanel.setPreferredSize(new Dimension(250, 250));

		leftPanel.add(innerLeftPanel);
		rightPanel.add(detailsPanel);
		
		rightPanel.setBorder(BorderFactory.createEmptyBorder (5, 1, 50,50));
		
		mainUsersPanel.add(leftPanel);
		mainUsersPanel.add(rightPanel);

		return mainUsersPanel;
	}

	/** Database connection variables**/
	String url ="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
	String user = "root";
	String password = "root";	
	
	
	/**
	 * Returns the list model retrieves from the SQL queries.
	 * Also uses the composite pattern to add users to a group(in this case county)
	 * and stores the user objects in the groups.
	 * @return
	 */
	public DefaultListModel<String> getDublinListModel(){
		DefaultListModel<String> dublinListModel = new DefaultListModel<String>();	
		UserComponent dublinGroup = new UserGroup("Dublin");

		try
		{			
		    //Create a connection.
			Connection dbConnection = DriverManager.getConnection(url, user, password);	

			PreparedStatement getDublin = dbConnection.prepareStatement("SELECT * FROM user where county = 'Dublin' ");
			ResultSet dublinRes = getDublin.executeQuery();

			while(dublinRes.next()){
				userName = dublinRes.getString("name");
				userCounty = dublinRes.getString("county");
				id = dublinRes.getString("id");
				
				dublinGroup.add(new User(userName, userCounty, id));		
				dublinCount++;
			}		
			
			usersArrayDublin = new User[dublinCount];

			ResultSet dublinRes2 = getDublin.executeQuery();
			int i = 0;
			while(dublinRes2.next()){
				userName = dublinRes2.getString("name");
				userCounty = dublinRes2.getString("county");
				id = dublinRes2.getString("id");
				usersArrayDublin[i] = new User(userName, userCounty, id);
				i++;
			}

			
			/** Dublin **/
			UserListMaker m = new UserListMaker(dublinGroup);
			ArrayList<UserComponent>dublinList = m.getAllUsers();		

			for(int x= 0; x < dublinList.size(); x++){
					String s = dublinList.get(x).getUserName();
					dublinListModel.addElement(s.toString());
			}

			//Check if empty
			if(dublinListModel.isEmpty()){
				dublinListModel.addElement("**No one from Dublin has registered **");
				return dublinListModel;
			}
		}
		catch (Exception e) {}
		return dublinListModel;
	}	
		
		
	/**
	 * Same as DublinListModel but for county cork.
	 * @return
	 */
	public DefaultListModel<String> getCorkListModel(){
		
		DefaultListModel<String> corkListModel = new DefaultListModel<String>();	
		UserComponent corkGroup = new UserGroup("Cork");
		
		/** Database connection variables**/
		String url ="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "root";
		try
		{			
		    //Create a connection.
			Connection dbConnection = DriverManager.getConnection(url, user, password);	
			
			PreparedStatement getCork = dbConnection.prepareStatement("SELECT * FROM user where county = 'Cork'");
			ResultSet corkRes = getCork.executeQuery();

			//Loop through the Cork users			
			while(corkRes.next()){
				userName = corkRes.getString("name");
				userCounty = corkRes.getString("county");
				id = corkRes.getString("id");

				corkGroup.add(new User(userName, userCounty, id));
				corkCount++;
			}	
			
			usersArrayCork = new User[corkCount];
			
			ResultSet corkRes2 = getCork.executeQuery();

			int i = 0;
			while(corkRes2.next()){
				userName = corkRes2.getString("name");
				userCounty = corkRes2.getString("county");
				id = corkRes2.getString("id");

				usersArrayCork[i] = new User(userName, userCounty, id);				
				i++;
			}	
				

			/** Dublin **/
			UserListMaker m2 = new UserListMaker(corkGroup);
			ArrayList<UserComponent>corkList = m2.getAllUsers();		

			for(int x = 0;x < corkList.size(); x++){
					String s = corkList.get(x).getUserName();
					corkListModel.addElement(s.toString());
			}

			if(corkListModel.isEmpty()){
				corkListModel.addElement("**No one from Cork has registered **");
				return corkListModel;
			}
		}
		catch (Exception e) {}
		return corkListModel;
}	
	
	
	
public DefaultListModel<String> getGalwayListModel(){
	DefaultListModel<String> galwayListModel = new DefaultListModel<String>();	
	UserComponent galwayGroup = new UserGroup("Galway");
	try
	{			
	    //Create a connection.
		Connection dbConnection = DriverManager.getConnection(url, user, password);	
		
		PreparedStatement getGalway = dbConnection.prepareStatement("SELECT * FROM user where county = 'Galway' ");
		ResultSet galwayRes = getGalway.executeQuery();

		//Loop through the Galway
		while(galwayRes.next()){
			userName = galwayRes.getString("name");
			userCounty = galwayRes.getString("county");
			id = galwayRes.getString("id");

			galwayGroup.add(new User(userName, userCounty, id));			
			galwayCount++;
		}					
		
		usersArrayGalway = new User[galwayCount];
		ResultSet galwayRes2 = getGalway.executeQuery();

		int i = 0;
		while(galwayRes2.next()){
			userName = galwayRes2.getString("name");
			userCounty = galwayRes2.getString("county");
			id = galwayRes2.getString("id");

			usersArrayGalway[i] = new User(userName, userCounty, id);
			i++;
		}	
	
		UserListMaker m3 = new UserListMaker(galwayGroup);
		ArrayList<UserComponent>galwayList = m3.getAllUsers();		

		for(int x = 0; x< galwayList.size(); x++){
				String s = galwayList.get(x).getUserName();
				galwayListModel.addElement(s.toString());
		}
	
		if(galwayListModel.isEmpty()){
			galwayListModel.addElement("**No one from Galway has registered **");
			return galwayListModel;
		}
	}
	catch (Exception e) {}
	return galwayListModel;
}	



/**
 * Button listener classes for dublin, cork and galway.
 * There classes update the ListModel of the Jlist for the
 * according country
 * @author aaron
 *
 */
	public class DublinListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			currentUsers = "Dublin";
			
			userList.setModel(getDublinListModel());
			scrollPane.add(userList);
		}
	}
	
	public class CorkListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			currentUsers = "Cork";

			userList.setModel(getCorkListModel());
			scrollPane.add(userList);
		}
	}	
	
	public class GalwayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			currentUsers = "Galway";
			
			userList.setModel(getGalwayListModel());
			scrollPane.add(userList);
		}
	}
	
	
	
	/**
	 * Listener for the JList that takes the index of the Jlist clicked, 
	 * and creates a new user object from the array of User objects
	 * 
	 * 
	 * @author aaron
	 *
	 */
	public class UserListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			@SuppressWarnings("unchecked")
			JList<String> list = (JList<String>) e.getSource();
			int listSelected = list.getSelectedIndex();

			//Checks the current list is of the right county
			if(currentUsers.equals("Dublin")){
				//Create a new user Object of the User object in the list index
				User thisUser = usersArrayDublin[listSelected];
				//Update labels
				userNameLabel.setText("Name: " + thisUser.getUserName());
				userCountyLabel.setText("County: "  + thisUser.getCountyName());		
				userIDLabel.setText("User ID: " + thisUser.getUserID());
			}
			else if (currentUsers.equals("Cork")){
				User thisUser = usersArrayCork[listSelected];
				userNameLabel.setText("Name: " + thisUser.getUserName());
				userCountyLabel.setText("County: "  + thisUser.getCountyName());
				userIDLabel.setText("User ID: " + thisUser.getUserID());
			}
			else	if(currentUsers.equals("Galway")){				
				User thisUser = usersArrayGalway[listSelected];
				userNameLabel.setText("Name: " + thisUser.getUserName());
				userCountyLabel.setText("County: "  + thisUser.getCountyName());
				userIDLabel.setText("User ID: " + thisUser.getUserID());
			}		
		}		
	}
}

