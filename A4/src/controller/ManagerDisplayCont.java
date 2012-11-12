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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
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
        
        ArrayList<String> names = new ArrayList<String>() ;
        String result = "";
		
		
	try{
             // http post
             HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/getManagers.php");
             HttpResponse response = httpclient.execute(httppost);
             HttpEntity entity = response.getEntity();
             InputStream is = entity.getContent();
			
             //convert response to string
             BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
             StringBuilder sb = new StringBuilder();
             String line = null;
             while ((line = reader.readLine()) != null) {
	         sb.append(line + "\n");
             }
             is.close();
	 
             result=sb.toString();
	        
             // parse JSON data and add to list
             JSONArray jArray = new JSONArray(result);
             for(int i=0;i<jArray.length();i++){
	                JSONObject json_data = jArray.getJSONObject(i);
                        if (json_data.getString("username").equals(user)) {
                            String password = new String(pass) ;
                            if(json_data.getString("password").equals(password)) {
                                System.out.print(json_data.getString("username") + " had logged in.") ;
                                return true ;
                        }
                  }   
             }
        }catch(Exception e){
            System.err.println("Error in login "+e.toString());
        }
        
        return false ;
    }
    
    /*
     * Format for .POS_MENU files
     * 
     * Name of Menu
     * all of the items in one line. Format example:       Burger-8.99&&&Salad-5.99&&&Dessert-3.99
     */
    public static boolean createMenu(ArrayList<String> menu) {		
        String items = "" ;

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("menu", menu.get(0)));
        for(int i=1; i < menu.size() ; i++){
            items += menu.get(i) ;
        }
        nameValuePairs.add(new BasicNameValuePair("items", items)) ;
		
	try{
            // http post
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/createMenu.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

	}catch(Exception e){
            System.err.println("Error in getting menu items "+e.toString());
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
        Menu currentMenu = new Menu(menuName);
        try {
            BufferedReader content = new BufferedReader(new InputStreamReader(new FileInputStream("menuNames.POS_MENU")));
            String line ;
            while((line = content.readLine()) != null){
                if(line.equals(menuName) || menuName.equals("menuNames")){
                    //Found the existing menu
                    //load file and menu
                    BufferedReader menuReader = new BufferedReader(new InputStreamReader(new FileInputStream(menuName + ".POS_MENU")));
                    int lineNumber = 0;
                    String menuLine;
                    while((menuLine = menuReader.readLine()) != null){
                        if(lineNumber == 0){
                            lineNumber ++;
                            continue;
                        }
                        
                        String menuItems[] = menuLine.split("&&&");
                        
                        for(String x : menuItems) {
                            String item[] = x.split("-");
                            MenuItem menuItem = new MenuItem(item[0], Double.parseDouble(item[1]));
                            currentMenu.addMenuItem(menuItem);
                        }    
                    }
                    System.out.println(currentMenu);
                    break;
                }
            }
        
        }catch (Exception e){}
        
        return currentMenu;
    }
    
    private static void saveMenu(Menu menu){
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
              s+= menu.getItem(i).getSaveString() + "&&&" ;
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
		
        String result = "";

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("menu", menuName));
		
	try{
            // http post
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/getMenu.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            //convert response to string
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
            }
            is.close();

            result=sb.toString();

            // parse JSON data and add to list
            JSONArray jArray = new JSONArray(result);
            loadMenu.add(jArray.getJSONObject(0).getString("name")) ;
            String elements[] = jArray.getJSONObject(1).getString("items").split("&&&");
            loadMenu.addAll(Arrays.asList(elements));
	}catch(Exception e){
            System.err.println("Error in getting menu items "+e.toString());
	}
		
	return loadMenu ;
    }
    
    public static boolean deleteMenu(String menuName) {		
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("menu", menuName));
        		
	try{
            // http post
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/deleteMenu.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            
            //convert response to string
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
            }
            is.close();

            String result=sb.toString();
            
            System.out.print(result) ;

	}catch(Exception e){
            System.err.println("Error in getting menu items "+e.toString());
	}
        
        return true ;
    }
    
}
