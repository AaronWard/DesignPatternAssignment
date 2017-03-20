package panelsPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cakeBuilder.Cake;
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
	
	JLabel userNameLabel;
	JLabel userCountyLabel;
	JLabel userFavouriteCakeLabel;
	
	String userName; 
	String userCounty;
	String usersFavrouriteCake;
	
	int userCount;
	
	JList<String> userList;
	DefaultListModel<String> listModel;
	Cake [] users;
	

	@Override
	public JPanel getPanel() {
		getUserDetails();
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
//		myCakeList = new JList<String>();		
//		myCakeList.addListSelectionListener(new CakeListListener());
//		
//		myCakeList.setFont(new Font("Century Gothic", Font.PLAIN, 24));
//		myCakeList.setBackground(MainDriver.northBackground);
//		myCakeList.setForeground(Color.white);
//		
//        JScrollPane scrollPane = new JScrollPane(myCakeList);		
//		innerLeftPanel.add(scrollPane);
		
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
		userFavouriteCakeLabel = new JLabel("Favourite Cake: ");
		
		userNameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 26));
		userCountyLabel.setFont(new Font("Century Gothic", Font.PLAIN, 26));
		userFavouriteCakeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 26));

		userNameLabel.setForeground(Color.WHITE);
		userCountyLabel.setForeground(Color.WHITE);
		userFavouriteCakeLabel.setForeground(Color.WHITE);
		
		detailsPanel.add(titlePanel);
		detailsPanel.add(userNameLabel);
		detailsPanel.add(userCountyLabel);
		detailsPanel.add(userFavouriteCakeLabel);
		
		detailsPanel.setBackground(MainDriver.northBackground);
		detailsPanel.setPreferredSize(new Dimension(250, 250));

		leftPanel.add(innerLeftPanel);
		rightPanel.add(detailsPanel);
		
		rightPanel.setBorder(BorderFactory.createEmptyBorder (5, 1, 50,50));
		
		mainUsersPanel.add(leftPanel);
		mainUsersPanel.add(rightPanel);

		return mainUsersPanel;
	}
	
	
	public void getUserDetails(){
		
		UserComponent dublinGroup = new UserGroup("Dublin");
		UserComponent corkGroup = new UserGroup("Cork");
		UserComponent galwayGroup = new UserGroup("Galway");
		UserComponent allUsers = new UserGroup("All Users");
		
		
		/************** SQL ****************************/
			
		/** Database connection variables**/
		String url ="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "root";
		try
		{			
		    //Create a connection.
			Connection dbConnection = DriverManager.getConnection(url, user, password);	

			PreparedStatement getDublin = dbConnection.prepareStatement("SELECT * FROM user where county = 'Dublin' ");
			ResultSet dublinRes = getDublin.executeQuery();
			
			PreparedStatement getCork = dbConnection.prepareStatement("SELECT * FROM user where county = 'Cork'");
			ResultSet corkRes = getCork.executeQuery();
			
			PreparedStatement getGalway = dbConnection.prepareStatement("SELECT * FROM user where county = 'Galway' ");
			ResultSet galwayRes = getGalway.executeQuery();

			//Loop through the Dublin users
			while(dublinRes.next()){
				userName = dublinRes.getString("name");
				userCounty = dublinRes.getString("county");
					
				dublinGroup.add(new User(userName, userCounty));		
			}			
			
			//Loop through the Cork users
			while(corkRes.next()){

				userName = dublinRes.getString("name");
				userCounty = dublinRes.getString("county");
				
				corkGroup.add(new User(userName, userCounty));
			}	
			
			//Loop through the Galway
			while(galwayRes.next()){
				userName = dublinRes.getString("name");
				userCounty = dublinRes.getString("county");
				galwayGroup.add(new User(userName, userCounty));
			}						
			
			allUsers.add(dublinGroup);
			allUsers.add(corkGroup);
			allUsers.add(galwayGroup);
					
			UserListMaker m = new UserListMaker(dublinGroup);
			m.getUserList();
			ArrayList<UserComponent> dublinList = m.getAllUsers();
			System.out.println(dublinList.size());		
		}
		catch (Exception e) {}
	}	
}
