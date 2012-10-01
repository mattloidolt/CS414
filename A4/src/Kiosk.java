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
		Dimension size = new Dimension(800, 600) ;
		// TODO: determine way to get these menu names automatically from the restaurant
		String[] menuNames = { "Menu1", "Menu2", "Menu3" };
		
		
        //Create and set up the window.
        JFrame frame = new JFrame("PIZZA_STORE_NAME Kiosk: " + kioskID);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //TODO: add content here
        JLabel label = new JLabel("Select Menu: ");
        frame.getContentPane().add(label, BorderLayout.NORTH);
        JComboBox menusList = new JComboBox(menuNames);
        menusList.setSelectedIndex(0);
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
