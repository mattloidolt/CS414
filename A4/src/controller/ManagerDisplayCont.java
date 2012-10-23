/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import core.Menu;
import core.MenuItem;
import java.io.*;
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
    
    // this is used by first creating a list of items to edit, then send that and the menu name to this method
    // the method will add those items or just change their price if they already exist, if remove=false
    // if remove=true, the method will remove all of the items in the list from the menu
    public static void editMenu(boolean remove, ArrayList<String> items, String menuName) {
        Menu m  = convertToMenu(KioskCont.getMenu(menuName));
        for(int i = 0 ; i < items.size() ; i++){
            String[] x = items.get(i).split("-") ;
            MenuItem item = new MenuItem(x[0], Double.parseDouble(x[1])) ;
            if(m.hasItem(item)){
                if(remove) {
                    m.removeMenuItem(item.getName());
                }
                else{
                    // to change the price of an item the old one is removed and a new one is added
                    m.removeMenuItem(item.getName());
                    m.addMenuItem(item);
                }
            }
            else{
                if(remove) {
                    System.out.println("Item: " + items.get(i) + " not removed because it was not on the menu.") ;
                }
                else{
                    m.addMenuItem(item);
                }
            }
        }
        
        // NOW FOR EDITING THE ACTUAL FILE.....
        deleteMenu(m.getName()) ;
        createMenu(convertToAL(m)) ;
    }
    
    public static void editItem(String menuItem) {
        
    }
    
    public static void removeItem(String menuItem) {
        
    }
    
    public static ArrayList<String> convertToAL(Menu m) {
        ArrayList<String> ret = new ArrayList<String>() ;
        ret.add(m.getName());
        for(int i =0 ; i < m.getNumberOfItems() ; i++) {
            ret.add(m.getItem(i).getName() + "-" + m.getItem(i).getPrice()) ;
        }
        return ret ;
    }
    
    public static Menu convertToMenu(ArrayList<String> menuAL) {
        Menu m = new Menu(menuAL.get(0)) ;
        for(int i =1 ; i < menuAL.size() ; i++){
            String[] split = menuAL.get(i).split("-") ;
            MenuItem item = new MenuItem(split[0], Double.parseDouble(split[1])) ;
            m.addMenuItem(item);
        }
        return m ;
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
