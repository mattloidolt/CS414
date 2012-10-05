package HW4;
/**
 * This should be the event dispatch thread. Handling other threads.
 * Should also be the manager's view GUI.
 */

import java.util.ArrayList;

import javax.swing.*;


public class Restaurant {
	public String restaurantName ;
	public String restaurantLocation ;
	public String restaurantPhoneNum ;
	private ArrayList<Menu> menuList;
	private ArrayList<Manager> managerList;
	
	public Restaurant() {
		menuList = new ArrayList<Menu>();
		managerList = new ArrayList<Manager>();
	}
	
	public Restaurant(String fileName) {
		
	}
	
	public void addMenu(Menu menu) {
		this.menuList.add(menu);
	}
	
	public ArrayList<Menu> getMenuList() {
		return this.menuList;
	}
}