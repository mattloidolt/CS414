/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import core.Menu;
import core.MenuItem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    
    public static String getMenuItemList(String menuName){
        Menu menu = loadMenu(menuName);
        String string = "<html><h1>Menu Items</h1><br>";
        for (MenuItem item : menu.getMenuItems()){
            string += item + "<br>";
        }
        string += "</html>";
        return string;
    }
    
    public static void createMenuItem(String menuName, String itemName, double itemPrice){
        Menu menu = loadMenu(menuName);
        MenuItem item = new MenuItem(itemName, itemPrice);
        menu.addMenuItem(item);
        saveMenu(menu);
    }
    
    public static void editItem(String menuName, String itemName, double itemPrice) {
        Menu menu = loadMenu(menuName);
        menu.getItemOfName(itemName).setPrice(itemPrice);
        saveMenu(menu);
    }
    
    public static void removeItem(String menuName, String itemName) {
        Menu menu = loadMenu(menuName);
        menu.removeMenuItem(itemName);
        saveMenu(menu);
    }
    
    private static Menu loadMenu(String menuName) {
        String menuItems[];
        Menu currentMenu = null;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, items FROM menus WHERE name = '" + menuName + "'");
            
            while(rs.next()){
                currentMenu = new Menu(rs.getString(1));
                menuItems = rs.getString(2).split("&&&");
                MenuItem toAdd;
                for(int i = 0; i < menuItems.length; ++i) {
                    toAdd = new MenuItem(menuItems[i].split("-")[0], Double.parseDouble(menuItems[i].split("-")[1]));
                    currentMenu.addMenuItem(toAdd);
                }
            }
            
            stmt.close() ;
            rs.close() ;
            conn.close() ;
        }
        catch (Exception e){
            System.err.println(e) ;
        }
        
        return currentMenu;
    }
    
    private static void saveMenu(Menu menu){
         try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
                                       "user=pizzaStore&password=password");
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM menus WHERE name = '" + menu.getName() + "'");
            
            String insert = ("INSERT INTO menus (name, items) VALUES (?,?)");     
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setString(1, menu.getName());
            String s = "";
            for(int i = 0 ; i < menu.getMenuItems().size(); i++) {
                if(i < menu.getMenuItems().size()-1) {
                s+= menu.getItem(i).getSaveString() + "&&&" ;
              }
              else {
                  s += menu.getItem(i).getSaveString();
              }
            }
            ps.setString(2, s);
            ps.executeUpdate();
            
            ps.close();
            stmt.close() ;
            conn.close() ;
        } catch (Exception e){
            System.err.println(e) ;
        }
        //Erase current menu file
        try {
            PrintWriter writer = new PrintWriter(menu.getName() + ".POS_Menu");
            writer.print("");
            writer.close();
        }catch(Exception e){}
        
        try {
          // creating the file for the menu
          PrintWriter out = new PrintWriter(new FileWriter(menu.getName() + ".POS_MENU", true));
          out.println(menu.getName()) ;
          out.flush();
          // building the items list string
          String s = "";
          for(int i = 0 ; i < menu.getMenuItems().size(); i++) {
              if(i < menu.getMenuItems().size()-1) {
                s+= menu.getItem(i).getSaveString() + "&&&" ;
              }
              else {
                  s += menu.getItem(i).getSaveString();
              }
          }
          out.println(s) ;
          out.close() ;
        }
        catch (Exception e) {
            System.err.println(e) ;
        }
        System.out.println("Menu " + menu.getName() + " edited.") ;
//        populateListText();
        
    }
    
    public static ArrayList<String> convertToAL(Menu m) {
        ArrayList<String> ret = new ArrayList<String>() ;
        ret.add(m.getName());
        ArrayList<MenuItem> items = new ArrayList<MenuItem>() ;
        for(int i =0 ; i < items.size() ; i++) {
            ret.add(items.get(i).getName() + "-" + items.get(i).getPrice()) ;
        }
        return ret;
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
