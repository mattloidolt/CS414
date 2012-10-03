package HW4;
/**
 * This should be a single instance of a kiosk. Should be run as it's own
 * thread. May use its own worker threads to make the GUI more interactive.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Kiosk {
	
	public static int kioskID = 0;
	private static GridBagConstraints gBC = new GridBagConstraints();
	private static JFrame frame = new JFrame("PIZZA_STORE_NAME Kiosk: " + kioskID);


	private static void createAndShowGUI() {
		// variables
		Color background = new Color(240, 125, 110) ;
		final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
        // Create and set up the window.
        //TODO: Remove this and find way so that user cannot close window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout()); // this is the framing structure
        gBC.fill = GridBagConstraints.HORIZONTAL;
        
        //###############  CONTENT  ######################
        // top line
        JLabel label = new JLabel("Select Menu: ");
        gBC.weightx = 0.2 ;
        gBC.gridx = 0 ;
        gBC.gridy = 0 ;
        frame.getContentPane().add(label, gBC);
        
        // drop down to select menu
        String[] menuNames = {"Select a Menu", "Menu 1", "Menu 2", "Menu 3"} ;
        JComboBox menusList = new JComboBox(menuNames);
        menusList.setSelectedIndex(0);
        gBC.weightx = 0.8 ;
        gBC.gridx = 1;
        gBC.gridy = 0;
        menusList.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
            {
        		JComboBox cb = (JComboBox)e.getSource();
                String menuName = (String)cb.getSelectedItem();
                if(!menuName.equals("Select a Menu")){
                	JInternalFrame internalFrame = new JInternalFrame(menuName, false, true, false, false);
                	internalFrame.setBounds(0, 50, size.width, size.height);
                	internalFrame.setVisible(true);
                	internalFrame.show();
                	gBC.ipady = size.height-100;
                	gBC.weightx = 0 ;
                	gBC.gridwidth = 2;
                	gBC.gridx = 0 ;
                	gBC.gridy = 1 ;
                	frame.getContentPane().add(internalFrame, gBC) ;
                }
            }}) ;
        frame.getContentPane().add(menusList, gBC) ;
        
        
        // fill empty space
        gBC.ipady = size.height-100 ;
        gBC.weightx = 0 ;
        gBC.gridwidth = 3 ;
        gBC.gridx = 0 ;
        gBC.gridy = 1 ;
        frame.getContentPane().add(Box.createGlue(), gBC) ;
        
        
        
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
