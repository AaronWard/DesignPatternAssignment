package panelsPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.sql.*;

import main.MainDriver;
import panelFactory.PanelFactory;
import singleton.LoginSingleton;

public class MakeACakePanel extends PanelFactory implements ActionListener{

	JPanel mainCakeMakerPanel;
	JPanel leftPanel, rightPanel;
	JPanel innerLeftPanel, innerRightPanel;
	JPanel picturePanel, ingredientsPanel;
	
	JComboBox<String> shapeCombo;
	JComboBox<String> typeCombo;
	JComboBox<String> toppingsCombo;
	JComboBox<String> sizeCombo;

	JLabel pickedTypeLabel;
	JLabel pickedShapeLabel;
	JLabel pickedToppingsLabel;
	JLabel pickedSizeLabel;
	
	JLabel imageLabel;
	
	JButton submitButton;
	JButton resetButton;
	
	String type, shape, topping, size = null;
	
	
	
	@Override
	public JPanel getPanel() {
		
		//String values for the JComboBoxes
		String[] typeArray ={"Chocolate", "Creampie", "Butter Cake", "Sponge Cake"};
		String [] shapeArray = {"Square", "Circular"};
		String [] toppingsArray = {"Sprinkles", "Crushed Cookies", "Chocolate Chips", "Vanilla Cream"};
		String [] sizeArray = {"6''", "8''", "10''", "12''"};
		
		/** GUI **/
		mainCakeMakerPanel = new JPanel();
		mainCakeMakerPanel.setLayout(new GridLayout(1,2));
		leftPanel = new JPanel();
		rightPanel= new JPanel(new GridLayout(2, 1));
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
		JLabel titleLabel = new JLabel("Choose your igredients and type");
		titleLabel.setFont(new Font("Century Gothic", Font.ITALIC, 24));
		
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(Color.WHITE);
		labelPanel.add(titleLabel);
		labelPanel.setPreferredSize(new Dimension(500, 40));
		
		
		//Area where user chooses ingredients and specifications
		JPanel dropDownPanel = new JPanel();
		dropDownPanel.setBackground(MainDriver.northBackground);
		dropDownPanel.setLayout(new GridLayout(5, 1));
		
		typeCombo = new JComboBox<String>(typeArray);
		shapeCombo = new JComboBox<String>(shapeArray);
		toppingsCombo = new JComboBox<String>(toppingsArray);
		sizeCombo = new JComboBox<String>(sizeArray);
		
		typeCombo.addActionListener(new TypeListener());
		shapeCombo.addActionListener(new ShapeListener());
		toppingsCombo.addActionListener(new ToppingsListener());
		sizeCombo.addActionListener(new SizeListener());
		
		
		JPanel typePanel = new JPanel(new GridLayout(2, 1));
		JPanel shapePanel = new JPanel(new GridLayout(2, 1));
		JPanel toppingsPanel = new JPanel(new GridLayout(2, 1));
		JPanel sizePanel = new JPanel(new GridLayout(2, 1));
		
		
		typePanel.setBackground(MainDriver.northBackground);
		shapePanel.setBackground(MainDriver.northBackground);
		toppingsPanel.setBackground(MainDriver.northBackground);
		sizePanel.setBackground(MainDriver.northBackground);
		
		
		JLabel typeLabel = new JLabel("\n\u2022Choose type of cake");
		JLabel shapeLabel = new JLabel("\n\u2022Choose shape of cake");
		JLabel toppingsLabel = new JLabel("\n\u2022What toppings would you like?");
		JLabel sizeLabel = new JLabel("\n\u2022How big to you want it?");
		
		typeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		shapeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		toppingsLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		sizeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));

		typeLabel.setForeground(Color.white);
		shapeLabel.setForeground(Color.white);
		toppingsLabel.setForeground(Color.white);
		sizeLabel.setForeground(Color.white);
		
		typePanel.add(typeLabel);
		typePanel.add(typeCombo);
		shapePanel.add(shapeLabel);
		shapePanel.add(shapeCombo);
		toppingsPanel.add(toppingsLabel);
		toppingsPanel.add(toppingsCombo);
		sizePanel.add(sizeLabel);
		sizePanel.add(sizeCombo);

		
		
		dropDownPanel.add(typePanel);
		dropDownPanel.add(shapePanel);
		dropDownPanel.add(toppingsPanel);
		dropDownPanel.add(sizePanel);
		
		innerLeftPanel.add(labelPanel, BorderLayout.CENTER);
		innerLeftPanel.add(dropDownPanel, BorderLayout.CENTER);
		
		/** Right Panel **/
		picturePanel = new JPanel();		
		imageLabel = new JLabel();
		picturePanel.add(imageLabel);
		picturePanel.setBackground(MainDriver.northBackground);
		ingredientsPanel = new JPanel(new GridLayout(5,1));
		
		pickedTypeLabel = new JLabel("Type: ");
		pickedShapeLabel = new JLabel("Shape: ");
		pickedToppingsLabel = new JLabel("Toppings: ");
		pickedSizeLabel = new JLabel("Size: ");
		
		pickedTypeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		pickedShapeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		pickedToppingsLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		pickedSizeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));

		pickedTypeLabel.setForeground(Color.WHITE);
		pickedShapeLabel.setForeground(Color.WHITE);
		pickedToppingsLabel.setForeground(Color.WHITE);
		pickedSizeLabel.setForeground(Color.WHITE);

		
		ingredientsPanel.add(pickedTypeLabel);
		ingredientsPanel.add(pickedShapeLabel);
		ingredientsPanel.add(pickedToppingsLabel);
		ingredientsPanel.add(pickedSizeLabel);
		
		/** Submit Button**/
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(MainDriver.northBackground);
		submitButton = new JButton("Submit");
		resetButton = new JButton("Reset");
		
		submitButton.setBackground(Color.WHITE);
		resetButton.setBackground(Color.WHITE);
		
		submitButton.addActionListener(this);
		resetButton.addActionListener(this);
		
		buttonsPanel.add(submitButton);
		buttonsPanel.add(resetButton);
		
		
		ingredientsPanel.add(buttonsPanel);
		ingredientsPanel.setBackground(MainDriver.northBackground);
		ingredientsPanel.setPreferredSize(new Dimension(250, 250));

		leftPanel.add(innerLeftPanel);
		rightPanel.add(picturePanel);
		rightPanel.add(ingredientsPanel);
		
		rightPanel.setBorder(BorderFactory.createEmptyBorder (5, 1, 50,50));
		
		mainCakeMakerPanel.add(leftPanel);
		mainCakeMakerPanel.add(rightPanel);

		
		return mainCakeMakerPanel;
	}
	
	/** If submit button is clicked **/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "Reset"){			
			pickedShapeLabel.setText("Shape: ");
			pickedSizeLabel.setText("Size :");
			pickedToppingsLabel.setText("Toppings: ");
			pickedTypeLabel.setText("Type: ");
						
			type = null; size = null; topping = null; shape = null;
		}
		else if (e.getActionCommand() == "Submit"){
			
			if(LoginSingleton.getId() == null){
				JOptionPane.showMessageDialog(null,"You need to be logged in to create a custom cake\nLogin and try again.", "Login Needed", JOptionPane.ERROR_MESSAGE);	
				}
			else{
				if(type == null || size == null || topping == null || shape == null){
					JOptionPane.showMessageDialog(null,"You need to select all entries to create a cake", "Woops", JOptionPane.ERROR_MESSAGE);	
				}
				else{
					if (JOptionPane.showConfirmDialog(null, "You want a " + size.toLowerCase() + " " + shape.toLowerCase() + " " +
							type.toLowerCase() + " with " +topping.toLowerCase() + " toppings.\n Is this correct?", "Confirm cake",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							addToDatabase();
					} 
				}
			}
		}
	}
	
	/**
	 * method used to add cake to the database
	 */
	private void addToDatabase(){
		
		String url ="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "root";
		
		try{
			//1 - get a connection to the driver
			Connection dbConnection = DriverManager.getConnection(url, user, password);
			//2 - create a statement
			Statement myStatement = dbConnection.createStatement();
			
			//Update database
			String sqlStatement =  "INSERT INTO cake"
					+ "(id, type, size, toppings, shape)"
					+ "VALUES ('" + LoginSingleton.getId() + "','" + type +"','"+ size + "','" + topping + "','" + shape + "')";
			myStatement.executeUpdate(sqlStatement);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}	
	
	/**
	 * 
	 * @author aaron
	 *
	 */
	public class TypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			
			/** Add picture to picture panel **/
			if(imageLabel != null){
                picturePanel.remove(imageLabel);            
			}
			
			URL url;
			if(cb.getSelectedItem() == "Chocolate"){
			       url = this.getClass().getResource("../choc.jpg");
			       type = (String) cb.getSelectedItem();			       
			}else if(cb.getSelectedItem() == "Creampie"){
			       url = this.getClass().getResource("../cream.jpg");
			       type = (String) cb.getSelectedItem();			       
			}else if (cb.getSelectedItem() == "Butter Cake"){
			       url = this.getClass().getResource("../butter.jpg");
			       type = (String) cb.getSelectedItem();
			}else{
			       url = this.getClass().getResource("../sponge.jpg");
			       type = (String) cb.getSelectedItem();
			}
			
			/**
			 * Set the displaying image to the GUI
			 */
	        Icon icon = new ImageIcon(url);
	        imageLabel = new JLabel(icon);
			picturePanel.add(imageLabel);
			pickedTypeLabel.setText("Type: " + cb.getSelectedItem());
			
			System.out.println("TYPE : " + type);
			
		}		
	}
	
	public class ShapeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			System.out.println(cb.getSelectedItem());
			pickedShapeLabel.setText("Shape: " + cb.getSelectedItem());

			shape =  (String) cb.getSelectedItem();			       
		}
	}
	public class ToppingsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			System.out.println(cb.getSelectedItem());
			pickedToppingsLabel.setText("Toppings: " + cb.getSelectedItem());

		    topping = (String) cb.getSelectedItem();			       
		}
	}
	public class SizeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			System.out.println(cb.getSelectedItem());
			pickedSizeLabel.setText("Size: " + cb.getSelectedItem());

	       size = (String) cb.getSelectedItem();			       
		}
	}
}
