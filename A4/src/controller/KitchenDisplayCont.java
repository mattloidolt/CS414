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
		String order = "";
		String result = "";


		try{
			URL url = new URL("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/getOrder.php");
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String tmp;
			while ((tmp = in.readLine()) != null){
				result = tmp.substring(tmp.indexOf("items")+8, tmp.length()-6);
			}
			in.close();


			order = "<html> <table>" ;
			String[] list = result.split("&%&");			
			for(String item: list){
				order += "<tr> " + item + "</tr>" ;
			}
			order += "</table> <br> </html>" ;

			JLabel o = new JLabel(order) ;
			o.setForeground(Color.white) ;
			orders.add(o) ;

		}catch(Exception e){
			System.err.println("Error in getting menu names "+e.toString());
		}

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



