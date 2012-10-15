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
	scanner.setIncludes(new String[]{"**/*.POS_ORDER"});
	scanner.setBasedir(".");
	scanner.setCaseSensitive(false);
	scanner.scan();
	final String[] files = scanner.getIncludedFiles();
	for(int i=0; i< files.length; i++) {
            BufferedReader in;
            String order = "<html><h2>" ;
            try {
            	in = new BufferedReader(new FileReader(files[i]));
            	order += in.readLine() + "</h2><table>" ;
		String line = in.readLine() ;
		int lineNum = 1 ;
		while(line != null){
                    if(lineNum != 1) {
                        order += "<tr>" ;
			if (lineNum == 2)
                            order += "Phone: " ;
			order += line + "</tr>" ;
                    }
                    lineNum++ ;
                    line = in.readLine() ;
		}
		order += "</table></html>" ;
		in.close();
            } catch (Exception e) {
		e.printStackTrace();
            }
            JLabel o = new JLabel(order) ;
            o.setForeground(Color.white) ;
            orders.add(o) ;
	}
        
        return orders ;
    }
    
}
