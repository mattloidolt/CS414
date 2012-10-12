/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author mattloidolt
 */
public class ManagerDisplayCont {
    
    public boolean login(String user, String pass) {
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
}
