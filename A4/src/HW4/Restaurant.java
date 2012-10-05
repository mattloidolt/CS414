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
	private ArrayList<Cashier> cashierList;
	private ArrayList<Chef> chefList;
	private ArrayList<Ingredient> ingredients;
	
	public Restaurant() {
		menuList = new ArrayList<Menu>();
		managerList = new ArrayList<Manager>();
		ingredients = new ArrayList<Ingredient>();
	}
	
	public Restaurant(String fileName) {
		
	}
	
	public void testInitialization() {
		Manager bob = new Manager("Bob", this);
		managerList.add(bob);
		Menu menu = new Menu("Breakfast", bob) ;

		MenuItem eggs = new MenuItem("Eggs", 5.99) ;
		MenuItem pancakes = new MenuItem("Pancakes", 6.99) ;
		MenuItem toast = new MenuItem("Toast and Jam", 3.99) ;
		MenuItem burrito = new MenuItem("Burrito", 6.99) ;
		MenuItem frenchToast = new MenuItem("French Toast", 5.99) ;
		MenuItem omelet = new MenuItem("Omelet", 7.99) ;
		menu.addMenuItem(eggs) ;
		menu.addMenuItem(pancakes) ;
		menu.addMenuItem(toast) ;
		menu.addMenuItem(burrito) ;
		menu.addMenuItem(frenchToast) ;
		menu.addMenuItem(omelet) ;
		addMenu(menu);
	}
	
	public ArrayList<String> getCurrentMenuItemNames() {
		ArrayList<String> names = new ArrayList<String>();
		Menu currentMenu = menuList.get((menuList.size()-1));
		for(int i = 0; i < currentMenu.getNumberOfItems(); ++i) {
			names.add(currentMenu.getItem(i).getName());
		}
		return names;
	}
	
	public ArrayList<Double> getCurrentMenuItemPrices() {
		ArrayList<Double> prices = new ArrayList<Double>();
		Menu currentMenu = menuList.get((menuList.size()-1));
		for(int i = 0; i < currentMenu.getNumberOfItems(); ++i) {
			prices.add(currentMenu.getItem(i).getPrice());
		}
		return prices;
	}
	
	public String getCurrentMenuName() {
		return menuList.get(menuList.size()-1).getName();
	}
	
	public void addMenu(Menu menu) {
		menuList.add(menu);
	}
	
	public ArrayList<Menu> getMenuList() {
		return this.menuList;
	}
}