/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.* ;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import org.apache.tools.ant.DirectoryScanner;

/**
 *
 * @author mattloidolt
 */
public class KitchenDisplayCont {
    
    public static ArrayList<JLabel> getOrdersToDisplay(){
        ArrayList<JLabel> orders = new ArrayList<JLabel>() ;
        
		String result = "";


		try{
			URL url = new URL("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/getOrder.php");
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String tmp;
			while ((tmp = in.readLine()) != null){
				result = tmp.substring(1, tmp.length()-2);
			}
			in.close();
			
                        System.out.println(result) ;
                        
                        String o[] = result.split("{\"") ;
			
			String[] list = result.split("\",\"");			
			for(String item: list){
				item = item.substring(9, item.length());
//				orders.add(item);
			}

		}catch(Exception e){
			System.err.println("Error in getting menu names "+e.toString());
		}

        
        
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
//                                           "user=pizzaStore&password=password");
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT id, name, items FROM orders ;");
//            while(rs.next()) {
//                String order = "<html><h2>" + rs.getString(2) + "</h2> <table>" ;
//                String[] items = rs.getString(3).split("&%&") ;
//                order += "<!--&&&&" + rs.getString(1) + "&&&&-->";
//                for (int i=0 ; i < items.length ; i++) {
//                    order += "<tr> " + items[i] + "</tr>" ;
//                }
//                order += "</table> <br> </html>" ;
//                JLabel o = new JLabel(order) ;
//                o.setForeground(Color.white) ;
//                orders.add(o) ;
//            }
//            stmt.close() ;
//            rs.close() ;
//            conn.close() ;
//            
//        } catch (Exception ex) {
//            System.err.println(ex) ;
//        }
        return orders ;

    }
    
    public static boolean archiveOrder(String orderID) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                           "user=pizzaStore&password=password");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM orders WHERE id='" + orderID + "' ;");
        }
        catch(Exception e){
            System.err.println(e);
        }
        return true ;        
    }
}



