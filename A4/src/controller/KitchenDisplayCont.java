/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import core.Menu;
import core.MenuItem;
import java.awt.Color;
import java.io.* ;
import javax.swing.JLabel;
import org.apache.tools.ant.DirectoryScanner;
import java.util.* ;

/**
 *
 * @author mattloidolt
 */
public class KitchenDisplayCont {
    
    public static ArrayList<JLabel> getOrdersToDisplay(){
        
        ArrayList<JLabel> orders = new ArrayList<JLabel>() ;
        
        DirectoryScanner scanner = new DirectoryScanner() ;
	scanner.setIncludes(new String[]{"*.POS_ORDER"});
	scanner.setBasedir(".");
	scanner.setCaseSensitive(false);
	scanner.scan();
	final String[] files = scanner.getIncludedFiles();
	for(int i=0; i< files.length; i++) {
            BufferedReader in;
            String order = "<html><h2>" ;
            try {
            	in = new BufferedReader(new FileReader(files[i]));
                order += "<!--&&&&" + in.readLine() + "&&&&-->";
            	order += in.readLine() + "</h2><table>" ;
		String line = in.readLine() ;
		int lineNum = 2 ;
		while(line != null){
                    if (lineNum == 3 || lineNum == 4 || lineNum == 5 || lineNum == 6) {
                        /// this is to skip displaying all the info to the cooks
                    }
                    else {
                        order += "<tr>" ;
                        if (lineNum == 2) {
                            order += "Phone: " ;
                        }
                        if (lineNum > 6) {
                            line = line.split("-")[0] ;
                        }
                        order += line + "</tr>" ;
                    }
                    if (lineNum == 6) {
                        order += "<tr> </tr>" ;
                    }
                    lineNum++ ;
                    line = in.readLine() ;
		}
		order += "</table><br></html>" ;
		in.close();
            } catch (Exception e) {
		System.err.println(e) ;
            }
            JLabel o = new JLabel(order) ;
            o.setForeground(Color.white) ;
            orders.add(o) ;
	}
        
        return orders ;
    }
    
    public static boolean archiveOrder(String orderID) {
        try{
            File file = new File(orderID + ".POS_ORDER");
            File dir = new File("oldOrders/");
            // Move file to new directory
            boolean success = file.renameTo(new File(dir, file.getName()));
            if (!success) {
                System.err.println("Error: Could not archive order: " + orderID) ;
            }
        }
        catch(Exception e){
            System.err.println(e);
        }
        return true ;        
    }
}



