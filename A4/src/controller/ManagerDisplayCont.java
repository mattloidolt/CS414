/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import core.Menu;
import core.MenuItem;
import java.sql.*;
import java.util.*;
/**
 *
 * @author mattloidolt
 */
public class ManagerDisplayCont {
    
    private static String userName ;
    
    public static String getUserName() {
        return userName ;
    }
    
    public static boolean login(String user, char[] pass) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, password FROM managers");
            while(rs.next()){
                System.out.println(rs.getString(1)) ;
                if (rs.getString(1).equals(user)) {
                    String password = new String(pass) ;
                    if(rs.getString(2).equals(password)) {
                        userName = rs.getString(1) ;
                        System.out.print(userName + " had logged in.") ;
                        return true ;
                    }
                }
            }
            stmt.close() ;
            rs.close() ;
            conn.close() ;
        }
        catch (Exception e){
            System.err.println(e) ;
        }
        return false ;
    }
    
    /*
     * Format for .POS_MENU files
     * 
     * Name of Menu
     * all of the items in one line. Format example:       Burger-8.99:Salad-5.99:Dessert-3.99
     */
    public static boolean createMenu(ArrayList<String> menu) {
        //Create the output text
        String query = "INSERT INTO menus "
                + "(name, items) VALUES('" + menu.get(0) + "', '" ;
        
        try {
          // TODO: adding the menu to the menuNames tracker file
          
          // creating the file for the menu
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
          // building the items list string
          String s = "";
          for(int i = 1 ; i < menu.size(); i++) {
              s+= menu.get(i) + "&&&" ;
          }
          query += s + "') ;" ;
          Statement stmt = conn.createStatement();
          stmt.executeUpdate(query);
          stmt.close() ;
          conn.close() ;
          System.out.println("Menu " + menu.get(0) + " created.") ;
        }
        catch (Exception e) {
            System.err.println(e) ;
            return false ;
        }
        return true ;
    }
    
    public static void editMenu(ArrayList<String> menu) {
        
        deleteMenu(menu.get(0)) ;
        createMenu(menu) ;
    }
    
    
    // different getMenu method which only find the object names
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
            for (int i=0; i < elements.length; i++){
                loadMenu.add(elements[i].split("-")[0]) ;
            }
            stmt.close() ;
            rs.close() ;
            conn.close() ;
        }
        catch(Exception e) {
            System.err.print(e);
        }
        return loadMenu ;
    }
    
    // this deletes the menu's .POS_MENU file and removes it from the menuNames.POS_MENU file
    public static boolean deleteMenu(String menuName) {
        boolean isFound = false ;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM menus WHERE name='" + menuName + "' ;") ;
            stmt.close() ;
            conn.close() ;
            System.out.println("\nMenu " + menuName + " deleted.") ;
        }
        catch (Exception e) {
             System.err.println(e) ;
        }
        return isFound ;
    }
    
}
