package com.CS414.pizzasrus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
	private static final String url = "jdbc:mysql://localhost:3306/pizza";
	private static final String user = "pizzaStore";
	private static final String password = "password";
	Connection connection = null;

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
