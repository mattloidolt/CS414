/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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


/**
 *
 * @author mattloidolt, Zach McGaughey
 */
public class KioskCont {
	@SuppressWarnings("null")
	public static ArrayList<String> getMenuNames(){

		ArrayList<String> names = new ArrayList<String>() ;
		String result = "";


		try{
			URL url = new URL("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/getMenus.php");
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String tmp;
			while ((tmp = in.readLine()) != null){
				result = tmp.substring(1, tmp.length()-2);
			}
			in.close();
			
			
			String[] list = result.split(",");			
			for(String item: list){
				item = item.substring(9, item.length()-2);
				names.add(item);
			}

		}catch(Exception e){
			System.err.println("Error in getting menu names "+e.toString());
		}

		return names ;

	}

	public static ArrayList<String> getMenu(String menuName){
		ArrayList<String> loadMenu = new ArrayList<String>();
		String result = "";
		
		try{
			String urlParameter = "menu=" + menuName;
			URL url = new URL("http://www.cs.colostate.edu/~loidolt/ExWorkFiles/getMenu.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameter.getBytes().length));
			connection.setUseCaches (false);
			
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameter);
			wr.flush();
			wr.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String tmp;
			while ((tmp = in.readLine()) != null){
				result = tmp.substring(tmp.indexOf("items")+8, tmp.length()-6);
			}
			in.close();
			
			connection.disconnect();
			
			String[] list = result.split("&&&");
			loadMenu.add("");
			for(String item: list){
				loadMenu.add(item);
			}

		}catch(Exception e){
			System.err.println("Error in getting menu names "+e.toString());
		
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

                //convert response to string
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                }
                is.close();

                String result=sb.toString();

                System.out.println(result) ;

            }catch(Exception e){
                    System.err.println("Error in saving order "+e.toString());
            }
            if (success) {
                    System.out.println("Order saved") ;
            }
            return success ;
       }
}
