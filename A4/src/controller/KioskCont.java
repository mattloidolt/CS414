/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public class KioskCont {
    public static ArrayList<String> getMenuNames(){
        ArrayList<String> names = new ArrayList<String>() ;
        String result = "";

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("year","1980"));

        try{
                // http post
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/getMenus.php");
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
                names.add(json_data.getString("name")) ;
        }
        }catch(Exception e){
                System.err.println("Error in getting menu names "+e.toString());
        }

        return names ;
    }
    
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
            loadMenu.add(jArray.getJSONObject(0).getString("menu")) ;
            String elements[] = jArray.getJSONObject(1).getString("items").split("&&&");
            loadMenu.addAll(Arrays.asList(elements));
	}catch(Exception e){
            System.err.println("Error in getting menu items "+e.toString());
	}
		
	return loadMenu ;
    }
    
    /*
     * saves the order to the orders database
     * all items are in one field in the database called 'items' delimited by '&%&'
     */
    public static boolean saveOrder(ArrayList<String> orderItems) {
        
        boolean success = true ;		
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("name", orderItems.get(0)));
        nameValuePairs.add(new BasicNameValuePair("phone", orderItems.get(0)));
        nameValuePairs.add(new BasicNameValuePair("address", orderItems.get(0)));
        nameValuePairs.add(new BasicNameValuePair("nameOnCard", orderItems.get(0)));
        nameValuePairs.add(new BasicNameValuePair("CCnum", orderItems.get(0)));
        nameValuePairs.add(new BasicNameValuePair("expDate", orderItems.get(0)));
        String items = "" ;
        for (int i=6; i < orderItems.size(); i++) {
                items += orderItems.get(i) + "&%&" ;
        }
        nameValuePairs.add(new BasicNameValuePair("order", items));


        try{
                // http post
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/saveOrder.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                InputStream is = entity.getContent();

        }catch(Exception e){
                System.err.println("Error in saving order "+e.toString());
        }
        if (success) {
                System.out.println("Order saved") ;
        }
        return success ;
    }
}
