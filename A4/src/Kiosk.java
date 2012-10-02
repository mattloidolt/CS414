/**
 * This should be a single instance of a kiosk. Should be run as it's own
 * thread. May use its own worker threads to make the GUI more interactive.
 */

import java.awt.*;
import javax.swing.*;


public class Kiosk {
	
	public static int kioskID = 0;

	private static void createAndShowGUI() {
		// variables
		Color background = new Color(240, 125, 110) ;
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		
		
        // Create and set up the window.
        JFrame frame = new JFrame("PIZZA_STORE_NAME Kiosk: " + kioskID);
        //TODO: Remove this and find way so that user cannot close window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout()); // this is the frame
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.HORIZONTAL;
        
 
        //###############  CONTENT  ######################
        // top line
        JLabel label = new JLabel("Select Menu: ");
        gBC.anchor = GridBagConstraints.PAGE_START ;
        gBC.weightx = 0.25 ;
        gBC.gridx = 0 ;
        gBC.gridy = 0 ;
        frame.getContentPane().add(label, gBC);
        
        // drop down to select menu
        String[] menuNames = {"Menu 1", "Menu 2", "Menu 3"} ;
        JComboBox menusList = new JComboBox(menuNames);
        menusList.setSelectedIndex(0);
        gBC.weightx = 0.75 ;
        gBC.gridx = 1;
        gBC.gridy = 0;
        frame.getContentPane().add(menusList, gBC) ;
        
        // setting up internal frame to display actual menu
        
        
        
        
        frame.setAlwaysOnTop(true) ;
        frame.setMinimumSize(size) ;
        frame.setBackground(background) ;
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

}
