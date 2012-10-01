/**
 * This should be a single instance of a kiosk. Should be run as it's own
 * thread. May use its own worker threads to make the GUI more interactive.
 */

import javax.swing.*;


public class Kiosk {
	
	public int kioskID ;

	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PIZZA_STORE_NAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);
 
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
