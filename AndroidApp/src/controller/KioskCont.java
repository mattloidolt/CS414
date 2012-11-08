/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.*;

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
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
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
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
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
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?" +
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
