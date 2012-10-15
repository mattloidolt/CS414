/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.* ;
import java.util.* ;
import core.Menu ;
import controller.KioskCont ;
import core.MenuItem ;
/**
 *
 * @author mattloidolt
 */
public class ManagerDisplayCont {
    
    public static boolean login(String user, char[] pass) {
        try{
            FileInputStream inFile = new FileInputStream("managers.shadow");
            BufferedReader content = new BufferedReader(new InputStreamReader(inFile));
            String line ;
            while((line = content.readLine()) != null){
                String use[] = line.split(":") ;
                if(use[0].equals(user)){
                    if(use[1].equals(pass)){
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
    
    public static boolean createMenu(String menuName, ArrayList<String> items) {
        try {
          // adding the menu to the menuNames tracker file
          PrintWriter output = new PrintWriter(new FileWriter("menuNames.POS_MENU"));
          output.println(menuName) ;
          // creating the file for the menu
          PrintWriter out = new PrintWriter(new FileWriter(menuName + ".POS_MENU"));
          out.println(menuName) ;
          // building the items list string
          String s = "";
          for(int i = 0 ; i < items.size(); i++) {
              s+= items.get(i) + ":" ;
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
    
    public static void editMenu(boolean remove, ArrayList<String> items, String menuName) {
        boolean found = false ;
        Menu m = KioskCont.getMenu(menuName) ;
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
    }
    
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
