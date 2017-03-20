package panelsPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import main.MainDriver;
import panelFactory.PanelFactory;
import singleton.LoginSingleton;

/**
 * 
 * @author aaron
 *
 */
public class LoginPanel extends PanelFactory {

	JPanel outterPanel;
	JPanel mainLoginPanel;
	JTextField idField;
	JPasswordField passwordField;
	@Override
	public JPanel getPanel() {
	
		JPanel mainLoginPanel;				
		mainLoginPanel = new JPanel();
		mainLoginPanel.setBackground(new Color(51, 51, 51));			
		idField = new JTextField(20);
		passwordField = new JPasswordField(20);
		JLabel nameLabel = new JLabel("User ID");
		JLabel passLabel = new JLabel("Password");
		
		nameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		nameLabel.setForeground(Color.WHITE);
		passLabel.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		passLabel.setForeground(Color.WHITE);
		
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridLayout(2, 2, 10, 30));
		innerPanel.setBackground(new Color(51, 51, 51));
		
		
		innerPanel.add(nameLabel);
		innerPanel.add(idField);
		innerPanel.add(passLabel);
		innerPanel.add(passwordField);
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(51,51,51));
		formPanel.setLayout( new GridLayout(3, 1,0,0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(51,51,51));
		JButton submitButton = new JButton("Submit");
		submitButton.setBackground(Color.white);
		submitButton.addActionListener(new SubmitListener());
		buttonPanel.add(submitButton);
		
		
		JLabel loginTitle = new JLabel("Login");
		loginTitle.setForeground(Color.WHITE);
		loginTitle.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		
		formPanel.add(loginTitle);
		
		formPanel.add(innerPanel);
		formPanel.add(buttonPanel);
		
		
		
		mainLoginPanel.add(formPanel);
		mainLoginPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100,200));
		
		JPanel regPanel = new JPanel();
		JLabel regLabel = new JLabel("Dont have an account? Register Here:");
		regLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		regLabel.setForeground(Color.WHITE);
		JButton regButton = new JButton("Register");
		regButton.setBackground(Color.WHITE);
		regButton.addActionListener(new RegButtonListener());
		regPanel.add(regLabel);
		regPanel.add(regButton);
			
		outterPanel = new JPanel();
		outterPanel.setLayout(new BoxLayout(outterPanel, BoxLayout.Y_AXIS));
		outterPanel.add(mainLoginPanel);
		outterPanel.add(regPanel);
		
		regPanel.setBackground(MainDriver.northBackground);
		outterPanel.setBackground(MainDriver.northBackground);


		return outterPanel;
	}
	
	/**
	 * If the user clicked the registration button
	 * @author aaron
	 *
	 */
	public class RegButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//the main panel is set to the Registration panel
			MainDriver.centrePanel.setVisible(false);
			MainDriver.panel = new RegisterPanel();
			MainDriver.centrePanel = MainDriver.panel.getPanel();
			MainDriver.mainPanel.add(MainDriver.centrePanel, BorderLayout.CENTER);
		}			
	}
	
	public class SubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
						
			/**
			 * Checks the sington to check if the user is already signed in.
			 * if true: then the user is prompted with an error message.
			 */
			if(LoginSingleton.getId() != null){
				System.out.println("You are already logged in mate");
				JOptionPane.showMessageDialog(null," You are already logged in as " + LoginSingleton.getName() +" with the ID: " + LoginSingleton.getId() + "\nSign out to log in as"
				+" another user.", "", JOptionPane.ERROR_MESSAGE);	
			}
			else{
					/** Check if fields are empty **/
					if(idField.getText()== "" || passwordField.getPassword().length == 0 ){
						JOptionPane.showMessageDialog(null,"Must enter all fields.", "Login Failure", JOptionPane.ERROR_MESSAGE);	
					}
					else{
						System.out.println("Log in attempted");
						String userID;
						
						
						/** Database connection variables**/
						String url ="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
						String user = "root";
						String password = "root";
						try
						{
							userID = idField.getText();
							String userPassword = String.valueOf(passwordField.getPassword());
						    String user1="";
						    String pass1="";
						    String userName = "";
						    
						    
						    //Create a connection.
							Connection dbConnection = DriverManager.getConnection(url, user, password);	
							//Select if the users ID and Password match that of one from the DB
							PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM user where id='"+
																userID+"' && password='"+userPassword+"'");
					        ResultSet res = stmt.executeQuery();
					        
					        //Loop through the result set
							while (res.next()) 
				            {
								//variables are set to compare with the users input
				                user1 = res.getString("id");
				                pass1 = res.getString("password");
				                userName = res.getString("name");
				            }
				            
				            	//If the users ID and password match IE. Login Successful
				            if (userID.equals(user1) && userPassword.equals(pass1)){
				            	
				            	//prompt with success message
								JOptionPane.showMessageDialog(null,"Login Successful.", "Welcome " + userName, JOptionPane.INFORMATION_MESSAGE);	
				    			MainDriver.logoutButton.setVisible(true);
				    			MainDriver.myCakesButton.setVisible(true);	
				    			
				    			//Set the instance of user
				    			 LoginSingleton.getLoginInstance(userID, userName);
				    		    			
				    			 //Bring user back to the home page
								MainDriver.centrePanel.setVisible(false);
								MainDriver.panel = new HomePanel();
								MainDriver.centrePanel = MainDriver.panel.getPanel();
								MainDriver.mainPanel.add(MainDriver.centrePanel, BorderLayout.CENTER);
				            }
				            /**
				             * if the users name or password do not match that of any in the DB
				             * then they are prompted with an error message and the fields
				             * are cleared.
				             */
				            else{
									JOptionPane.showMessageDialog(null,"Credentials not valid for login. Try again.", "Login Failure", JOptionPane.ERROR_MESSAGE);	
									idField.setText("");
									passwordField.setText("");
				            }
						}
						catch(SQLException e1){
							e1.printStackTrace();
					}
				}
			}
		}
	}
}
