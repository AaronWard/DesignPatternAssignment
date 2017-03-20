package panelsPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cakeBuilder.Cake;
import main.MainDriver;
import panelFactory.PanelFactory;
import singleton.LoginSingleton;

public class MyCakesPanel extends PanelFactory {

	JPanel mainCakesPanel;
	JPanel leftPanel, rightPanel;
	JPanel innerLeftPanel, innerRightPanel;
	JPanel picturePanel, ingredientsPanel;
	
	JLabel pickedTypeLabel;
	JLabel pickedShapeLabel;
	JLabel pickedToppingsLabel;
	JLabel pickedSizeLabel;
	
	JLabel imageLabel;
	
	String type; 
	String shape;
	String size;
	String toppings;
	
	int cakeCount;
	
	JList<String> myCakeList;
	DefaultListModel<String> listModel;
	Cake [] cakesArray;
	
	
	@Override
	public JPanel getPanel() {
		getCakes();
		mainCakesPanel = new JPanel();
		
		mainCakesPanel.setLayout(new GridLayout(1,2));
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
		myCakeList = new JList<String>(getCakes());		
		myCakeList.addListSelectionListener(new CakeListListener());
		
		myCakeList.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		myCakeList.setBackground(MainDriver.northBackground);
		myCakeList.setForeground(Color.white);
		
        JScrollPane scrollPane = new JScrollPane(myCakeList);		
		innerLeftPanel.add(scrollPane);
		
		/** Right Panel **/
		picturePanel = new JPanel();		
		imageLabel = new JLabel();
		picturePanel.add(imageLabel);
		picturePanel.setBackground(MainDriver.northBackground);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(MainDriver.northBackground);
		
		JLabel detailsLabel = new JLabel("Cake Details");
		detailsLabel.setFont(new Font("Century Gothic", Font.ITALIC, 24));
		detailsLabel.setForeground(Color.WHITE);
		detailsLabel.setBackground(MainDriver.northBackground);
		
		titlePanel.add(detailsLabel);
		
		ingredientsPanel = new JPanel(new GridLayout(6,1));		
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
		
		ingredientsPanel.add(titlePanel);
		ingredientsPanel.add(pickedTypeLabel);
		ingredientsPanel.add(pickedShapeLabel);
		ingredientsPanel.add(pickedToppingsLabel);
		ingredientsPanel.add(pickedSizeLabel);
		
		ingredientsPanel.setBackground(MainDriver.northBackground);
		ingredientsPanel.setPreferredSize(new Dimension(250, 250));

		leftPanel.add(innerLeftPanel);
		rightPanel.add(picturePanel);
		rightPanel.add(ingredientsPanel);
		
		rightPanel.setBorder(BorderFactory.createEmptyBorder (5, 1, 50,50));
		
		mainCakesPanel.add(leftPanel);
		mainCakesPanel.add(rightPanel);

		return mainCakesPanel;
	}
	
	public DefaultListModel<String> getCakes(){
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		/** Database connection variables**/
		String url ="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "root";
		try
		{			
		    //Create a connection.
			Connection dbConnection = DriverManager.getConnection(url, user, password);	

			PreparedStatement s = dbConnection.prepareStatement("SELECT type FROM cake where id='"+
					LoginSingleton.getId() +"'");
			ResultSet countRes = s.executeQuery();
	
			//Count how many cakes are stored in the database
			while(countRes.next()){
				cakeCount++;
			}
						
			/** Getting the cake details for the user **/
			PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM cake where id='"+
												LoginSingleton.getId() +"'");
	        ResultSet res = stmt.executeQuery();
	        
	        
	        //array to temporarily store the amount of cakes the user has
	        cakesArray = new Cake[cakeCount];
	        int index = 1;
	        int i = 0;
	        //Loop through the result set
			while (res.next()) 
            {
				//variables are set to compare with the users input
                type = res.getString("type");
                shape = res.getString("shape");
                size = res.getString("size");
                toppings = res.getString("toppings");
                
                //Creat a new cake object using the builder pattern taken from the SQL data.
        		Cake cake = new Cake.Builder().type(type).shape(shape).size(size).toppings(toppings).build();
        		
        		//Add cake to the array
        		cakesArray[i] = cake;
        		
        		//add the cake type to the list
        		listModel.addElement("Cake "+ index + ": " + cake.getType());    
        		
        		i++; index++;
            }
		}
		catch (Exception e) {}
		
		//Check if empty
		if(listModel.isEmpty()){
			listModel.addElement("** You have no cakes saved **");
			return listModel;
		}
		else{
			return listModel;
		}
	}
	
	/**
	 * ListSelectionListener class used to get the selected index in the list, from
	 * that index number -> retrieve the corresponding index in the cakes array and
	 * update the UI accordingly.
	 * @author aaron
	 *
	 */
	public class CakeListListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
		
			@SuppressWarnings("unchecked")
			//Check the index of the list selected
			JList<String> list = (JList<String>) e.getSource();
			int listSelected = list.getSelectedIndex();
			
			//Set the labels to the attributes in the cake object.
			//taken from the Cakes array
			Cake thisCake = cakesArray[listSelected];
			pickedTypeLabel.setText("Type: " + thisCake.getType());
			pickedShapeLabel.setText("Shape: "  + thisCake.getShape());
			pickedToppingsLabel.setText("Toppings: " + thisCake.getToppings());
			pickedSizeLabel.setText("Size: " + thisCake.getSize());	
			
			
			/** Checks what is the current cake and set the image URL **/
			URL url;
			if(thisCake.getType().equals("Chocolate")){
			       url = this.getClass().getResource("../choc.jpg");
			}else if(thisCake.getType().equals("Creampie")){
			       url = this.getClass().getResource("../cream.jpg");
			}else if (thisCake.getType().equals("Butter Cake")){
			       url = this.getClass().getResource("../butter.jpg");
			}else{
			       url = this.getClass().getResource("../sponge.jpg");
			}		
			
			if(imageLabel != null){
                picturePanel.remove(imageLabel);            
			}
			
			//Add image to panel
	        Icon icon = new ImageIcon(url);
	        imageLabel = new JLabel(icon);
			picturePanel.add(imageLabel);	
		}	
	}
}
