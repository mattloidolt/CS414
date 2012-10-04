package HW4;
/**
 * This should be a single instance of a kiosk. Should be run as it's own
 * thread. May use its own worker threads to make the GUI more interactive.
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;


public class Kiosk {
	
	public static int kioskID = 0;
	public static Restaurant restaurant = new Restaurant(); //#TODO:how do we initialize restaurant kiosk is in?
	private static GridBagConstraints gBC = new GridBagConstraints();
	private static JFrame frame = new JFrame("PIZZA_STORE_NAME Kiosk: " + kioskID);
	private static Order o = new Order() ;


	private static void createAndShowGUI() {
		// variables
		Color background = new Color(240, 125, 110) ;
		final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
        // Create and set up the window.
        //TODO: Remove this and find way so that user cannot close window
		final JDesktopPane desktop = new JDesktopPane();
		frame.setContentPane(desktop) ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desktop.setBackground(background) ;
        frame.setMinimumSize(size) ;
        desktop.setLayout(new GridBagLayout()); // this is the framing structure
        gBC.fill = GridBagConstraints.HORIZONTAL;
        
        
        
        
        //////// REMOVE - for building purposes ///////////
        Manager bob = new Manager(new Restaurant()) ;
        Menu menu1 = new Menu("Breakfast", bob) ;
        
        MenuItem eggs = new MenuItem("Eggs", 5.99) ;
        MenuItem pancakes = new MenuItem("Pancakes", 6.99) ;
        MenuItem toast = new MenuItem("Toast and Jam", 3.99) ;
        MenuItem burrito = new MenuItem("Burrito", 6.99) ;
        MenuItem frenchToast = new MenuItem("French Toast", 5.99) ;
        MenuItem omelet = new MenuItem("Omelet", 7.99) ;
        menu1.addMenuItem(eggs) ;
        menu1.addMenuItem(pancakes) ;
        menu1.addMenuItem(toast) ;
        menu1.addMenuItem(burrito) ;
        menu1.addMenuItem(frenchToast) ;
        menu1.addMenuItem(omelet) ;
        ///////////////////////////////////////////////////
        
        //###############  CONTENT  ######################
        gBC.gridx = 0 ;
        gBC.gridy = -1 ;
        gBC.weightx = 0.5 ;
        gBC.weighty = 0.25 ;
        for (int i=0; i<menu1.getMenuItems().size(); i++) {
        	//TODO: 'menu1' needs to be gathered dynamically (will probably require nesting this loop in another loop)
        	final MenuItem item = menu1.getMenuItems().get(i) ;
        	Button b = new Button(item.name + "\n$" + item.price) ;
        	b.setActionCommand(menu1.getMenuItems().get(i).name);
        	b.setPreferredSize(new Dimension(150, 100));
        	// set action
        	b.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			//TODO: change this error message to be displayed on screen
        			o.addItem(item) ;
        		}
        	}) ;
        	gBC.gridx = i%5 ;
        	if ((i%5) == 0)
        		gBC.gridy += 1 ;
        	desktop.add(b, gBC) ;
        }
        
        // ******* adding bottom bar **********
        frame.getContentPane().add(new JSeparator()) ;
        Button viewOrder = new Button("View Order") ;
        Button placeOrder = new Button("Place Order") ;
        viewOrder.setPreferredSize(new Dimension(150, 100));
        //TODO: background colors won't change for these buttons for some reason (tried setBackground and setForeground)
        viewOrder.setForeground(Color.green) ;
        placeOrder.setPreferredSize(new Dimension(150, 100));
        placeOrder.setForeground(Color.blue) ;
        // actions
        viewOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	JInternalFrame internalFrame = new JInternalFrame("Order Summary", false, true, false, false);
            	internalFrame.setBounds(0, 50, 500, size.height);
            	internalFrame.setBackground(new Color(225, 225, 255)) ;
            	gBC.ipady = size.height-100;
            	gBC.weightx = 0 ;
            	gBC.gridwidth = 4;
            	gBC.gridx = 0 ;
            	gBC.gridy = 0 ;
            	// adding content to internal frame
            	JLabel receipt = getReceipt() ;
            	internalFrame.add(receipt) ;
            	// displaying frame and bringing to front
            	//TODO: buttons keep showing up over the internal frame (not sure why)
            	internalFrame.show() ;
            	frame.add(internalFrame, gBC) ;
            	internalFrame.setVisible(true);
            	try {
					internalFrame.setSelected(true) ;
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
            }
        });
        placeOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	//TODO: this is where I left off on 10/3/12
            	//			receipt text is not displaying on second desktop
            	//			need to add spots to enter credit card info or cash amount
            	//			need to add spots to enter name, address, phone number (customer data)
            	//			need to add button to place order (or 'send to kitchen' or something)
            	//				|-   this button should check to see that all field are valid (if not display error/go back)
            	//				|-    then reset the Kiosk to a new order
                JDesktopPane desktop2 = new JDesktopPane() ;
                JLabel receipt = getReceipt() ;
            	gBC.weightx = 0 ;
            	gBC.gridheight = 2;
            	gBC.gridx = 0 ;
            	gBC.gridy = 0 ;
            	desktop2.add(receipt, gBC) ;
            	desktop2.setVisible(true) ;
                frame.setContentPane(desktop2) ;
                frame.setVisible(true) ;
            }
        });
        gBC.gridy += 1 ;
        gBC.gridx = 0 ;
        desktop.add(viewOrder, gBC) ;
        gBC.gridx = 1 ;
        desktop.add(placeOrder, gBC) ;
        
        
        
        
        
        
        
        
        /////// THIS SECTION WAS DEPRICATED - allows user to switch between menus ///////
        // top line
        /*JLabel label = new JLabel("Select Menu: ");
        gBC.weightx = 0.2 ;
        gBC.gridx = 0 ;
        gBC.gridy = 0 ;
        frame.getContentPane().add(label, gBC);
        
        // drop down to select menu
        ArrayList<Menu>menuList = restaurant.getMenuList();
        String[] menuNames = new String[menuList.size()+1];
        menuNames[0] = "Select a Menu";
        for(int i = 0; i < menuList.size(); i++){
        	menuNames[i+1] = menuList.get(i).menuName;
        }
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
        frame.getContentPane().add(Box.createGlue(), gBC) ;*/
        ////////////////////////////////////////////////////////////////////////////////
        
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static JLabel getReceipt() {
		ArrayList<OrderItem> orderItems = o.getOrderList() ;
    	String text = "<html><center><h1>ORDER SUMMARY</h1><br>" ;
    	text += "<table>" +
    			"<tr>" +
    			"<td>Item Name</td>" +
    			"<td>Quantity</td>" +
    			"<td>Unit Price</td>" +
    			"<td>Total Price</td>" +
    			"</tr>" ;
    	for(int i=0; i<orderItems.size(); i++) {
    		double lineTotal = orderItems.get(i).quantity * orderItems.get(i).getItem().price ;
    		text += "<tr><td>"+orderItems.get(i).getItem().name+"</td>" +
    				"<td>"+orderItems.get(i).quantity+"</td>" +
    				"<td>$"+orderItems.get(i).getItem().price+"</td>" +
    				"<td>$"+lineTotal+"</td></tr><br>" ;
    	}
    	DecimalFormat formatter = new DecimalFormat("#0.00");
    	String tot = formatter.format(o.total) ;
    	String payments = formatter.format(o.total-o.amountDue) ;
    	String amountDue = formatter.format(o.amountDue) ;
    	text += "<tr><td></td><td></td><td></td><td>Total: $"+tot+"</td></tr>" +
    			"<tr><td></td><td>Payments:</td><td></td><td>$"+payments+"</td></tr>" +
    			"<tr><td></td><td>Amount Due:</td><td></td><td>$"+amountDue+"</td><tr></table></center></html>" ;
    	JLabel recipt = new JLabel(text, JLabel.CENTER) ;
    	return recipt ;
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
