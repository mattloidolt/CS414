package com.CS414.pizzasrus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
	private static final String url = "jdbc:mysql://localhost:3306/pizza";
	private static final String user = "pizzaStore";
	private static final String password = "password";
	private static Connection connection = null;

	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			
			if(connection == null) {
				System.out.println("Connection failed");
				throw new Exception("PANTS");
			}
			Statement st = connection.createStatement();
			ResultSet results = st.executeQuery("select * from managers");
			while(results.next()) {
				System.out.println("ID " + results.getInt(1));
				System.out.println("Username " + results.getString(2));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getMenus() {
		ArrayList<String> retStrings = new ArrayList<String>();
		if(connection != null) {
			try {
				Statement st = connection.createStatement();
				ResultSet results = st.executeQuery("SELECT name FROM managers");		
				while(results.next()) {
					retStrings.add(results.getString(1));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return retStrings;
	}
	
	public void closeConnection() throws Exception{
		connection.close();
	}
	
	public static void main(String[] args) {
		try {
			DB test = new DB();
			test.closeConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
