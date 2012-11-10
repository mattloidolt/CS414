/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.*;

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
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+ localIP + "/pizza?" +
					"user=pizzaStore&password=password");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM menus ;");
			while(rs.next()){
				names.add(rs.getString(1)) ;
			}
			stmt.close() ;
			rs.close() ;
			conn.close() ;
		} catch (Exception ex) {
			System.err.println(ex) ;
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
		try{
			String pdriver = "com.mysql.jdbc.Driver";
			Class.forName(pdriver).newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+ localIP + "/pizza?" +
					"user=pizzaStore&password=password");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT items FROM menus WHERE name='" + menuName + "' ;");
			loadMenu.add(menuName) ;
			rs.next();
			String elements[] = rs.getString(1).split("&&&");
			loadMenu.addAll(Arrays.asList(elements));
			stmt.close() ;
			rs.close() ;
			conn.close() ;
		}
		catch(Exception e) {
			System.err.print(e);
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
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+ localIP + "/pizza?" +
					"user=pizzaStore&password=password");
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO orders "
					+ "(name, phone, address, nameOnCard, creditCardNumber, expirationDate, items) VALUES('" ;
			query += orderItems.get(0) + "', '" ; // name
			query += orderItems.get(1) + "', '" ; // phone number
			query += orderItems.get(2) + "', '" ; // address
			query += orderItems.get(3) + "', '" ; // nameOnCard
			query += orderItems.get(4) + "', '" ; // creditCardNum
			query += orderItems.get(5) + "', '" ; // expirationDate

			String items = "" ;
			//			for (int i=6; i < orderItems.size(); i++) {
			//				items += orderItems.get(i) + "&%&" ;
			//			}
			for (OrderItem item:currentOrder.getOrderList()) {
				items += item + "&%&";
			}

			query += items + "') ;" ;
			stmt.executeUpdate(query) ;
			stmt.close() ;
			conn.close() ;

		}
		catch(Exception e) {
			System.err.print(e);
			success = false ;
		}
		if (success) {
			System.out.println("Order saved") ;
		}
		return success ;
	}
}
