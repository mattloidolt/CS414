/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import core.Menu;
import core.MenuItem;
import java.io.*;
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
            FileInputStream inFile = new FileInputStream("managers.shadow");
            BufferedReader content = new BufferedReader(new InputStreamReader(inFile));
            String line ;
            while((line = content.readLine()) != null){
                String use[] = line.split(":") ;
                if(use[0].equals(user)){
                    String password = new String(pass) ;
                    if(use[1].equals(password)){
                        userName = user ;
                        System.out.println(user + " has logged in.") ;
                        return true ;
                    }
                }
            }
            content.close() ;
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
        try {
          // adding the menu to the menuNames tracker file
          PrintWriter output = new PrintWriter(new FileWriter("menuNames.POS_MENU"));
          output.println(menu.get(0)) ;
          // creating the file for the menu
          PrintWriter out = new PrintWriter(new FileWriter(menu.get(0) + ".POS_MENU"));
          out.println(menu.get(0)) ;
          // building the items list string
          String s = "";
          for(int i = 1 ; i < menu.size(); i++) {
              s+= menu.get(i) + ":" ;
          }
          out.println(s) ;
          out.close() ;
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
    
    public static ArrayList<String> convertToAL(Menu m) {
        ArrayList<String> ret = new ArrayList<String>() ;
        ret.add(m.getName());
        ArrayList<MenuItem> items = new ArrayList<MenuItem>() ;
        for(int i =0 ; i < items.size() ; i++) {
            ret.add(items.get(i).getName() + "-" + items.get(i).getPrice()) ;
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
        ArrayList<String> menus = KioskCont.getMenuNames() ;
        for (int i =0 ; i < menus.size(); i++) {
            if (menuName.equals(menus.get(i))){
                menus.remove(menus.get(i));
                isFound = true ;
            }
        }
        try {
            File f1 = new File("menuNames.POS_MENU");
            f1.delete() ;
            f1.createNewFile();
            PrintWriter out = new PrintWriter(new FileWriter("menuNames.POS_MENU"));
            for(int i = 0 ; i < menus.size() ; i++){
                out.println(menus.get(i)) ;
            }
            out.close() ;
        }
        catch (Exception e) {
             System.err.println(e) ;
        }
        return isFound ;
    }
    
}
