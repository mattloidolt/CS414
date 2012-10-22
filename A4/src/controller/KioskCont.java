/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.util.*;
import java.sql.* ; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mattloidolt
 */
public class KioskCont {
    public static ArrayList<String> getMenuNames(){
        ArrayList<String> names = new ArrayList<String>() ;
        try {
            // establishing a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
            Statement stmt = null;
            ResultSet rs = null;
            try{
                FileInputStream inFile = new FileInputStream("menuNames.POS_MENU");
                BufferedReader content = new BufferedReader(new InputStreamReader(inFile));
                String line ;
                while((line = content.readLine()) != null){
                    names.add(line);
                }
                content.close() ;
            }
            catch(Exception e){
                System.err.print(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KioskCont.class.getName()).log(Level.SEVERE, null, ex);
        }
        return names ;
    }
    
    public static ArrayList<String> getMenu(String menuName){
        ArrayList<String> loadMenu = new ArrayList<String>();
        try{
            BufferedReader content = new BufferedReader(new InputStreamReader(new FileInputStream(menuName + ".POS_MENU"))) ;
            String line ;
            int lineNumber = 0;
            while((line = content.readLine()) != null){
                if(lineNumber == 0) {
                    loadMenu.add(line) ;
                }else {
                    String elements[] = line.split("&&&");
                    for(int i = 0 ; i < elements.length; i++) {
                        loadMenu.add(elements[i]) ;
                    }    
                }
                lineNumber++ ;
            }
            content.close();
        }
        catch(Exception e) {
            System.err.print(e);
        }
        return loadMenu ;
    }
    
    /*
     * format for .POS_ORDER files: (name is OrderID.POS_ORDER)
     * 
     * OrderID
     * Name
     * Phone
     * Address
     * Name On Card
     * Credit Card Number
     * Expiration Date
     * ONE LINE FOR EACH ITEM ON THE ORDER
     */
    public static void saveOrder(ArrayList<String> orderItems) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter(orderItems.get(0) + ".POS_ORDER"));
            for (int i = 0 ; i < orderItems.size() ; i++){
                output.println(orderItems.get(i)) ;
            }
            output.close();
        }
        catch(Exception e) {
            System.err.print(e);
        }
    }
}
