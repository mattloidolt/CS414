/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.*;
/**
 *
 * @author mattloidolt
 */
public class KioskCont {
    public static ArrayList<String> getMenuNames(){
        ArrayList<String> names = new ArrayList<String>() ;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM menus ;");
            while(rs.next()){
                names.add(rs.getString(1)) ;
            }
            stmt.close() ;
            rs.close() ;
            conn.close() ;
        } catch (Exception ex) {
            System.err.println(ex) ;
        }
        return names ;
    }
    
    public static ArrayList<String> getMenu(String menuName){
        ArrayList<String> loadMenu = new ArrayList<String>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT items FROM menus WHERE name='" + menuName + "' ;");
            loadMenu.add(menuName) ;
            rs.next();
            String elements[] = rs.getString(1).split("&&&");
            loadMenu.addAll(Arrays.asList(elements));
            stmt.close() ;
            rs.close() ;
            conn.close() ;
        }
        catch(Exception e) {
            System.err.print(e);
        }
        return loadMenu ;
    }
    
    /*
     * saves the order to the orders database
     * all items are in one field in the database called 'items' delimited by '&%&'
     */
    public static boolean saveOrder(ArrayList<String> orderItems) {
        boolean success = true ;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO orders "
                + "(name, phone, address, nameOnCard, creditCardNumber, expirationDate, items) VALUES('" ;
            query += orderItems.get(0) + "', '" ; // name
            query += orderItems.get(1) + "', '" ; // phone number
            query += orderItems.get(2) + "', '" ; // address
            query += orderItems.get(3) + "', '" ; // nameOnCard
            query += orderItems.get(4) + "', '" ; // creditCardNum
            query += orderItems.get(5) + "', '" ; // expirationDate
            
            String items = "" ;
            for (int i=6; i < orderItems.size(); i++) {
                items += orderItems.get(i) + "&%&" ;
            }
            query += items + "') ;" ;
            stmt.executeUpdate(query) ;
            stmt.close() ;
            conn.close() ;
            
        }
        catch(Exception e) {
            System.err.print(e);
            success = false ;
        }
        if (success) {
            System.out.println("Order saved") ;
        }
        return success ;
    }
}
