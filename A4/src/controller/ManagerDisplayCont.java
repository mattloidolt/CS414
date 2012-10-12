/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.* ;
import HW4.Menu ;
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

        }
        catch (Exception e){
            System.err.println(e) ;
        }
        return false ;
    }
    
    public static boolean createMenu(String menuName, ArrayList<String> items) {
        try {
          FileInputStream inFile = new FileInputStream("menuNames.POS_MENU");
          BufferedReader content = new BufferedReader(new InputStreamReader(inFile));
          String line ;
          while((line = content.readLine()) != null){
              if(line.equals(menuName)){
                  
              }
          }
        }
        catch (Exception e) {
            System.err.println(e) ;
        }
    }
    
    
}
