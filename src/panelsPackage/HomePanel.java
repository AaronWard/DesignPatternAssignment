package panelsPackage;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import main.MainDriver;
import panelFactory.PanelFactory;

public class HomePanel extends PanelFactory {

	JPanel mainHomePanel, leftPanel, rightPanel, picturePanel, infoPanel,  timesPanel, addressPanel;
	
	@Override
	public JPanel getPanel() {
		
		/** Initialise panels **/
		mainHomePanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		picturePanel = new JPanel();
		infoPanel = new JPanel();
		timesPanel = new JPanel();
		addressPanel = new JPanel();
		
		/** Set Layouts of panels **/
		mainHomePanel.setLayout(new BorderLayout());
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BorderLayout());
		infoPanel.setLayout(new GridLayout());
		timesPanel.setLayout(new GridLayout(7, 2));
		addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.Y_AXIS));
		
		/** Left Panel **/
		JPanel labelPanel = new JPanel(new FlowLayout());
		JLabel aboutLabel = new JLabel("             "
				+ "		About Us");
		aboutLabel.setFont(new Font("Century Gothic", Font.ITALIC , 26));
		aboutLabel.setBackground(new Color(51, 51, 51));
		aboutLabel.setForeground(Color.BLACK);
		aboutLabel.setPreferredSize(new Dimension(300, 40));
		labelPanel.add(aboutLabel);
		
		JTextArea aboutText = new JTextArea();
		aboutText.setBackground(MainDriver.northBackground);
		aboutText.setEditable(false);
		aboutText.setLineWrap(true);
		aboutText.setWrapStyleWord(true);
		aboutText.setFont(new Font("Century Gothic", Font.PLAIN , 22));
		aboutText.setForeground( Color.BLACK);
		aboutText.setText("We at the Cake Shop Cafe began almost 30 years ago in the heart of Dublin. By providing delicious cakes and pastries, we have"
				+ "grown to be a very successful business, with almost 10 stores accross ireland. We offer the best of best when it comes to cakes, from our"
				+ "famous birthday to wedding cake, you are sure to find something you like.");

		leftPanel.add(labelPanel);
		leftPanel.add(aboutText);
		leftPanel.setPreferredSize(new Dimension(300, 590));
//		leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 0,,0));
	
		/*************************************/

        URL url = this.getClass().getResource("../CAKEGIF.gif");
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
		picturePanel.add(label);
        
		
		rightPanel.setBackground(new Color(51,51,51));
		picturePanel.setBackground(new Color(51,51,51));
        label.setBackground(new Color(51,51,51));
        
        
        /***********************************/
        	
        	String [] weekDaysArray = {"Monday","Tuesday", "Wednesday" , "Thursday", "Friday", "Saturday", "Sunday"};
        	String [] timesArray = {"9 - 6", "9 - 6", "9 - 6", "9 - 6", "9 - 3", "Closed"};
        	JLabel[] daylabels=new JLabel[14];
        	JLabel[] timelabels=new JLabel[14];

        	/**
        	 * This section uses nested for loops to create JLabels with the days of the week
        	 * and also times they are opened
        	 */
            for (int i=0;i< 6;i++){
            	daylabels[i]=new JLabel(weekDaysArray[i]);
            	daylabels[i].setFont(new Font("", Font.BOLD, 14));
            	daylabels[i].setForeground(Color.white);
                timesPanel.add(daylabels[i]);
                
                for (int x = 0; x < 6; x++){
                	timelabels[i]=new JLabel(timesArray[i]);
                	timelabels[i].setFont(new Font("", Font.BOLD, 14));
                	timelabels[i].setForeground(Color.white);
                }
                timesPanel.add(timelabels[i]);                
            }
           
        timesPanel.setBackground(MainDriver.northBackground);
        timesPanel.setFont(new Font("Century Gothic", Font.PLAIN , 22));
        
        //Shop Location Information
        String [] addressInfo = {"Cake Shop Cafe", "123 ITB Road", "Blanchardstown","01 888 654321"};
        JLabel [] addressLabels = new JLabel[4];
        for (int i = 0; i < 4; i++){
        	addressLabels[i]=new JLabel("\n\u2022  " + addressInfo[i]);
        	addressLabels[i].setFont(new Font("", Font.BOLD, 18));
        	addressLabels[i].setForeground(Color.white);
        	addressPanel.add(addressLabels[i]);
        }

        addressPanel.setBackground(MainDriver.northBackground);
        addressPanel.setFont(new Font("Century Gothic", Font.PLAIN , 22));
               
        /**********************************/
        
        Border infoBorder = BorderFactory.createTitledBorder(null, "Information", TitledBorder.CENTER, TitledBorder.TOP,
        		new Font("Century Gothic",Font.PLAIN,30), Color.WHITE);        
        infoPanel.setBorder(infoBorder);
        infoPanel.setBackground(new Color(51,51,51));
        infoPanel.setForeground(Color.WHITE);    	
  
        /** Add panels to other panels **/ 
        infoPanel.add(timesPanel, BorderLayout.WEST);
        infoPanel.add(addressPanel, BorderLayout.EAST);
		rightPanel.add(picturePanel, BorderLayout.NORTH);
		rightPanel.add(infoPanel, BorderLayout.SOUTH);

		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
		
		mainHomePanel.add(leftPanel, BorderLayout.WEST);
		mainHomePanel.add(rightPanel, BorderLayout.CENTER);
		mainHomePanel.setBackground(new Color(51,51,51));
		mainHomePanel.setBorder (BorderFactory.createEmptyBorder (50, 50, 20, 1));
		
		return mainHomePanel;
	}
}
