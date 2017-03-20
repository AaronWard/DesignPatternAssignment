package panelsPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

import javax.swing.*;

import main.MainDriver;
import main.RandomNumberGenerator;
import panelFactory.PanelFactory;
import singleton.LoginSingleton;

public class RegisterPanel extends PanelFactory{
	
		JPanel buttonPanel;
		JPanel outterPanel;
		JPanel formPanel;
		JPanel mainRegPanel;
		JPanel mainLoginPanel;
		JPanel innerPanel;
		
		JTextField nameField;
		JPasswordField passwordField, passwordField2;
		JComboBox<String> countyCombo;
		String[] countyList = {"Dublin", "Cork", "Galway"};
		
		String countyPick;
		
		@Override
		public JPanel getPanel() {
	
			mainLoginPanel = new JPanel();
			mainLoginPanel.setBackground(new Color(51, 51, 51));	
			
			
			/** JLabels and Input fields **/
			nameField = new JTextField(20);
			passwordField = new JPasswordField(20);
			passwordField2 = new JPasswordField(20);
			countyCombo = new JComboBox<String>(countyList);
			countyCombo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> cb = (JComboBox<String>) e.getSource();
					
					if(cb.getSelectedItem() == "Dublin"){
						countyPick = "Dublin";
					}else if(cb.getSelectedItem() == "Cork"){
						countyPick = "Cork";
					}
					else{
						countyPick = "Galway";
					}
				}
			});

			JLabel nameLabel = new JLabel("Name: ");
			JLabel passLabel = new JLabel("Choose a password: ");
			JLabel passLabel2 = new JLabel("Re-enter Password: ");
			JLabel countyLabel = new JLabel("County: ");
			
			
			nameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 22));
			nameLabel.setForeground(Color.WHITE);
			passLabel.setFont(new Font("Century Gothic", Font.PLAIN, 22));
			passLabel.setForeground(Color.WHITE);
			passLabel2.setFont(new Font("Century Gothic", Font.PLAIN, 22));
			passLabel2.setForeground(Color.WHITE);
			countyLabel.setFont(new Font("Century Gothic", Font.PLAIN, 22));
			countyLabel.setForeground(Color.WHITE);


			
			innerPanel = new JPanel();
			innerPanel.setLayout(new GridLayout(4, 2, 10, 30));
			innerPanel.setBackground(new Color(51, 51, 51));
					
			/** Add components to JPanel **/
			innerPanel.add(nameLabel);
			innerPanel.add(nameField);
			innerPanel.add(passLabel);
			innerPanel.add(passwordField);
			innerPanel.add(passLabel2);
			innerPanel.add(passwordField2);
			innerPanel.add(countyLabel);
			innerPanel.add(countyCombo);

			
			/**/
			formPanel = new JPanel();
			formPanel.setBackground(new Color(51,51,51));
			formPanel.setLayout( new GridLayout(3, 1,10,0));
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(new Color(51,51,51));
			JButton regButton = new JButton("Register");
			regButton.setBackground(Color.white);
			regButton.addActionListener(new RegisterListener());
			buttonPanel.add(regButton);
			
			JLabel regTitle = new JLabel("Registration");
			regTitle.setForeground(Color.WHITE);
			regTitle.setFont(new Font("Century Gothic", Font.PLAIN, 30));
			
			formPanel.add(regTitle);
			formPanel.add(innerPanel);
			formPanel.add(buttonPanel);
			
			mainLoginPanel.add(formPanel);
			mainLoginPanel.setBorder(BorderFactory.createEmptyBorder(5, 200, 200,200));
			mainLoginPanel.setBackground(new Color(51,51,51));
			
			outterPanel = new JPanel();
			outterPanel.setBackground(new Color(51, 51, 51));
			outterPanel.add(mainLoginPanel);			
			
			return outterPanel;
		}
		
		/**
		 * Button listener that registers the users when clicked
		 * Also checks if fields are empty and makes sure passwords match
		 * @author aaron
		 *
		 */
		public class RegisterListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(LoginSingleton.getId() != null){
					JOptionPane.showMessageDialog(null,"You must be logged out to register a user", "Registration Failure", JOptionPane.ERROR_MESSAGE);		
				}
				else{
					System.out.println("Log in attempted");
					String userID = new RandomNumberGenerator().generator();
					System.out.println(userID);
									
					//Check for empty fields
					if(nameField.getText().equals("") || passwordField.getPassword().length == 0 || passwordField2.getPassword().length ==0){
						JOptionPane.showMessageDialog(null,"Must enter all fields", "Registration Failure", JOptionPane.ERROR_MESSAGE);		
					}
					//check for matching passwords
					else if(!Arrays.equals(passwordField.getPassword(), passwordField2.getPassword())){
						JOptionPane.showMessageDialog(null, "Passwords do not match.", "Woops", JOptionPane.ERROR_MESSAGE);
						
							//set the text to null if this error occurs 
							passwordField.setText(null);
							passwordField2.setText(null);
					}
					else{
						
						String name = nameField.getText(); 
						String userPassword = String.valueOf(passwordField.getPassword());
						
						String url ="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
						String user = "root";
						String password = "root";
						
						try
						{
							//1 - get a connection to the driver
							Connection dbConnection = DriverManager.getConnection(url, user, password);
							//2 - create a statement
							Statement myStatement = dbConnection.createStatement();
							
							
							//Update database
							String sqlStatement = "INSERT INTO user"
									+ "(id, name, password, county)"
									+ "VALUES ('" + userID + "', '" + name +"','"+ userPassword +"','" + countyPick+ "')";
							
							myStatement.executeUpdate(sqlStatement);
						}
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
						
						//When the user successfully registers and the DB has been updated
						JOptionPane.showMessageDialog(null,"Your new login ID is " + userID, "Registration successfull", JOptionPane.ERROR_MESSAGE);	
						
						MainDriver.centrePanel.setVisible(false);
						MainDriver.panel = new HomePanel();
						MainDriver.centrePanel = MainDriver.panel.getPanel();
						MainDriver.mainPanel.add(MainDriver.centrePanel, BorderLayout.CENTER);
					}
				}
				
			}
	}
}
