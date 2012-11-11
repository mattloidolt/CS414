/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import core.Menu;
import core.MenuItem;
import core.Order;
import core.OrderItem;

/**
 *
 * @author mattloidolt
 */
public class KioskCont {
	static Order currentOrder = new Order();
	static LinkedList<MenuItem> orderHistory = new LinkedList<MenuItem>();
	static String localIP = "10.0.2.2";
	static ArrayList<core.Menu> menuList;

	//	static String localIP = "10.84.44.89";



	public static void setupDefaults() {
		//******************************************
		core.Menu breakfastMenu = new core.Menu("Breakfast");
		MenuItem cereal = new MenuItem("Cereal", 3.99);
		MenuItem biscuit = new MenuItem("Biscuit", 2.99);
		MenuItem orangeJuice = new MenuItem("Orange Juice", 1.99);
		MenuItem pancakes = new MenuItem("Pancakes", 6.99); 
		breakfastMenu.addMenuItem(cereal);
		breakfastMenu.addMenuItem(biscuit);
		breakfastMenu.addMenuItem(orangeJuice);
		breakfastMenu.addMenuItem(pancakes);

		//******************************************

		core.Menu lunchMenu = new Menu("Lunch");
		MenuItem sandwich = new MenuItem("Sandwich", 6.99);
		MenuItem burger = new MenuItem("Burger", 8.99);
		MenuItem salad = new MenuItem("Salad", 3.99);
		MenuItem coke = new MenuItem("Coke", 1.99);
		lunchMenu.addMenuItem(sandwich);
		lunchMenu.addMenuItem(burger);
		lunchMenu.addMenuItem(salad);
		lunchMenu.addMenuItem(coke);
		//******************************************

		menuList = new ArrayList<Menu>();
		menuList.add(breakfastMenu);
		menuList.add(lunchMenu);
	}

	public static String getPreviousMenu(String currentMenu) {
		for(int i = 0; i < menuList.size(); i++){
			if(menuList.get(i).menuName.equals(currentMenu)){
				if(i > 0){
					return menuList.get(i-1).menuName;
				} else
					return menuList.get(0).menuName;
			}
		}

		return null;
	}

	public static String getNextMenu(String currentMenu) {
		for(int i = 0; i < menuList.size(); i++){
			if(menuList.get(i).menuName.equals(currentMenu)){
				if(i < menuList.size()-1){
					return menuList.get(i+1).menuName;
				} else
					return menuList.get(menuList.size()-1).menuName;
			}
		}

		return null;
	}

	public static ArrayList<String> getDefaultMenuItems(String menuName){
		Menu menu = null;
		for(core.Menu menuSearch : menuList) {
			if (menuSearch.menuName.equals(menuName)){
				menu = menuSearch;
				break;
			}
		}
		ArrayList<String> menuItemList = new ArrayList<String>();
		menuItemList.add("NULL FOR MENU NAME");
		for(MenuItem item : menu.getMenuItems())
			menuItemList.add(item.name + "-" + item.price);

		return menuItemList;
	}

	public static ArrayList<String> getDefaultMenus(){
		ArrayList<String> list = new ArrayList<String>();
		for(core.Menu menu : menuList) {
			list.add(menu.menuName);
		}
		return list;
	}

	public static void addToOrder(String name, double price) {
		MenuItem menuItem = new MenuItem(name, price);
		currentOrder.addItem(menuItem);
		orderHistory.add(menuItem);
	}

	public static Order getOrder(){
		return currentOrder;
	}

	public static void removeLastOrderItem(){
		if (currentOrder.getNumItems() > 0) {
			currentOrder.undoAddItem(orderHistory.getLast());
			orderHistory.removeLast();
		}
	}

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


	// format of the return menu
	//
	//
	// menu name
	// item1 (ex: Burger-8.99)
	// item2
	// item3
	// ...
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

	/*
	 * saves the order to the orders database
	 * all items are in one field in the database called 'items' delimited by '&%&'
	 * 
	 * format of the orderItems arrayList:
	 * 
	 * name
	 * phone number
	 * address
	 * name on card
	 * credit card number
	 * expiration date
	 * item1 (ex: Burger-8.99)
	 * item2
	 * item3
	 * ...
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
		for (OrderItem item:currentOrder.getOrderList()) {
			items += item + "&%&";
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
