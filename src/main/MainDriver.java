package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import panelsPackage.HomePanel;
import panelsPackage.LoginPanel;
import panelsPackage.MyCakesPanel;


@SuppressWarnings("serial")
public class MainDriver extends JFrame {	
	
	public static JPanel mainPanel ;
	public JPanel northPanel;
	public JPanel southPanel;
	public JPanel eastPanel;
	public JPanel westPanel;
	public JPanel navBarPanel;
	public JPanel buttonPanel;
	public static JPanel centrePanel = new JPanel();
	public static PanelFactory panel, northFactoryPanel;

	
	public static JButton loginButton, logoutButton;
	public static JButton myCakesButton;
	boolean loggedIn = false;
	static int userID;	
	public static Color northBackground = new Color(102, 102, 102);
	
	public static void main(String [] args){
		new MainDriver();
	}
	
	public MainDriver(){
		super.setTitle("Cake Shop");
		add(getMainPanel());
		setSizes();
	}
	
	
	private void setSizes() {
		setSize(1200, 800);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Returns the main panelsPackage to the JFrame
	 * @return The JPanel
	 */
	public JPanel getMainPanel(){
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(getNorthPanel(), BorderLayout.NORTH);
		setCenterPanel();
		
		return mainPanel;
	}
	
	public void setNorthPanel(){
		northFactoryPanel = new HomePanel();
		northPanel = northFactoryPanel.getPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
	}
	
	/**
	 * The Centre panel is the initial panel that is displayed
	 * when the program is run
	 * @return
	 */
	public void setCenterPanel(){
		panel = new HomePanel();
		centrePanel = panel.getPanel();
		mainPanel.add(centrePanel, BorderLayout.CENTER);
	}
	
	
	
	
	public JPanel getNorthPanel(){
		
		northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(northBackground);

		ImageIcon icon = new ImageIcon(getClass().getResource("../cake_logo.png"));
		Image img = icon.getImage(); 
	 
		//Change the dimensions of the image
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB); 
		Graphics g = bi.createGraphics(); 
		g.drawImage(img, 600, 0, 180, 170, null); 
		ImageIcon newIcon = new ImageIcon(bi);
		 
		JLabel imgLabel = new JLabel();
		 imgLabel.setIcon(newIcon);
		 
		 JPanel imagePanel = new JPanel();
		 imagePanel.setBackground(northBackground);
		 imagePanel.setPreferredSize(new Dimension(300, 300));
		 imagePanel.add(imgLabel);
		 
		 loginButton = new JButton("Login");
		 loginButton.setBackground(Color.WHITE);
		 loginButton.setPreferredSize(new Dimension(150, 60));
		 loginButton.setFocusPainted(false);
		 loginButton.addActionListener(new LoginListener());
		 
		 logoutButton = new JButton("Log out");
		 logoutButton.setBackground(Color.WHITE);
		 logoutButton.setPreferredSize(new Dimension(150, 60));
		 logoutButton.setFocusPainted(false);
		 logoutButton.addActionListener(new LogoutListener());
		 logoutButton.setVisible(false);
		 
		 
		 
		 buttonPanel = new JPanel();
		 buttonPanel.setBackground(northBackground);
		 buttonPanel.setPreferredSize(new Dimension(300, 300));
		 buttonPanel.add(loginButton);
		 buttonPanel.add(logoutButton);
		 buttonPanel.setBorder (BorderFactory.createEmptyBorder (50, 50, 1, 1));

		 
		 northPanel.add(imagePanel, BorderLayout.WEST);
		 northPanel.add(buttonPanel, BorderLayout.EAST);
		 northPanel.add(getNavBarPanel(), BorderLayout.SOUTH);
		
		northPanel.setPreferredSize(new Dimension(1200,210));

		return northPanel;
	}

	/**
	 * bar to provide buttons to nagivate between sections within
	 * the program
	 * @return JPanel
	 */
	public JPanel getNavBarPanel(){
		
		navBarPanel = new JPanel();
		navBarPanel.setLayout(new FlowLayout());
		
		JButton homeButton = new JButton("Home");
		JButton recipesButton = new JButton("Recipes");
		JButton cakesButton = new JButton("Cakes");
		myCakesButton = new JButton("My Cakes");
		
		homeButton	.setPreferredSize(new Dimension(150, 30));
		recipesButton.setPreferredSize(new Dimension(150, 30));
		cakesButton.setPreferredSize(new Dimension(150, 30));
		myCakesButton.setPreferredSize(new Dimension(150, 30));
		myCakesButton.setVisible(false);
		
		
		homeButton.setBackground(Color.WHITE);
		recipesButton.setBackground(Color.WHITE);
		cakesButton.setBackground(Color.WHITE);
		myCakesButton.setBackground(Color.WHITE);

		
		homeButton.addActionListener(new HomeListener());
		recipesButton.addActionListener(new RecipesListener());
		cakesButton.addActionListener(new CakesListener());
		myCakesButton.addActionListener(new MyCakesListener());
		
	
		navBarPanel.add(homeButton);
		navBarPanel.add(recipesButton);
		navBarPanel.add(cakesButton);
		navBarPanel.add(myCakesButton);

		navBarPanel.setPreferredSize(new Dimension(1200, 30));
		navBarPanel.setBackground(new Color(51, 51, 51));

		return navBarPanel;
	}
	
	/****** Action Listeners *****/
	public class LoginListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
							
				centrePanel.setVisible(false);
				/** Change Panel **/
				panel = new LoginPanel();
				centrePanel = panel.getPanel();
				mainPanel.add(centrePanel, BorderLayout.CENTER);
			}
		}
	
	public class LogoutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
			
	}
	
		// When the home button is clicked, it brings you back to the
		// home page
		public class HomeListener implements ActionListener{
				
				public void actionPerformed(ActionEvent e) {
					centrePanel.setVisible(false);
					panel = new HomePanel();
					centrePanel = panel.getPanel();
					mainPanel.add(centrePanel, BorderLayout.CENTER);
				}
		}
		
		public class RecipesListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
			}
		}
		
		public class CakesListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
			}
		}
		
		public class MyCakesListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
}


