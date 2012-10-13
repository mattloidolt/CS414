/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import HW4.Menu ;
import HW4.MenuItem;
import java.io.* ;
import java.util.*;
/**
 *
 * @author mattloidolt
 */
public class KioskCont {
    public static ArrayList<String> getMenuNames(){
        ArrayList<String> names = new ArrayList<String>() ;
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
        return names ;
    }
    
    public static Menu getMenu(String menuName){
        Menu loadMenu = null;
        try{
            FileInputStream inFile = new FileInputStream(menuName + ".POS_MENU") ;
            BufferedReader content = new BufferedReader(new InputStreamReader(inFile)) ;
            String line ;
            int lineNumber = 0;
            while((line = content.readLine()) != null){
                if(lineNumber == 0) {
                    loadMenu = new Menu(line) ;
                }else {
                    String elements[] = line.split(":");
                    for(int i = 0 ; i < elements.length; i++) {
                        String it[] = elements[i].split("-") ;
                        MenuItem item = new MenuItem(it[0], Double.parseDouble(it[1])) ;
                        loadMenu.addMenuItem(item);
                    }
                    lineNumber++ ;
                }
            }
            content.close();
        }
        catch(Exception e) {
            System.err.print(e);
        }
        return loadMenu ;
    }
    /*
     * format for .POS_ORDER files:
     * 
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
