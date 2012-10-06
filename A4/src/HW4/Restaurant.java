package HW4;
/**
 * This should be the event dispatch thread. Handling other threads.
 * Should also be the manager's view GUI.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	
	public void initialization() {
//		Manager bob = new Manager("Bob", this);
//		managerList.add(bob);
//		Menu menu = new Menu("Breakfast", bob) ;
//
//		MenuItem eggs = new MenuItem("Eggs", 5.99) ;
//		MenuItem pancakes = new MenuItem("Pancakes", 6.99) ;
//		MenuItem toast = new MenuItem("Toast and Jam", 3.99) ;
//		MenuItem burrito = new MenuItem("Burrito", 6.99) ;
//		MenuItem frenchToast = new MenuItem("French Toast", 5.99) ;
//		MenuItem omelet = new MenuItem("Omelet", 7.99) ;
//		menu.addMenuItem(eggs) ;
//		menu.addMenuItem(pancakes) ;
//		menu.addMenuItem(toast) ;
//		menu.addMenuItem(burrito) ;
//		menu.addMenuItem(frenchToast) ;
//		menu.addMenuItem(omelet) ;
//		addMenu(menu);
		loadManagers();
		loadMenu();
	}
	
	// one manager per line in managers.txt stores name
	private void loadManagers() {
		try {
			FileInputStream inFile = new FileInputStream("managers.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));
			Manager toAdd = null;
			String str;
			String[] tokens;
			while((str = reader.readLine()) != null && str != "") {
				//tokens = str.split(" ");
				toAdd = new Manager(str, this);
				managerList.add(toAdd);
			}
		} catch(Exception f) {
			System.out.println("Error opening manager file.");
		}
	}
	
	private void loadIngredients() {
		try {
			FileInputStream inFile = new FileInputStream("ingredients.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inFile));
			Ingredient toAdd = null;
			String str;
			String[] tokens;
			while((str = reader.readLine()) != null && str != "") {
				tokens = str.split("_");
				toAdd = new Ingredient(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
				ingredients.add(toAdd);
			}
		} catch(Exception f) {
			System.out.println("Error opening ingredients file.");
		}
	}
	
	private void loadMenu() {
		String line;
		int lineNumber = 0;
		try {
			FileInputStream inFile = new FileInputStream("menus.POS_MENU");
			BufferedReader content = new BufferedReader(new InputStreamReader(inFile));
			Menu loadMenu = null;
			while((line = content.readLine()) != null){
				if(lineNumber == 0) {
					String elements[] = line.split("-");
					loadMenu = new Menu(elements[0], new Manager(elements[1], this));
					menuList.add(loadMenu);
					lineNumber ++;
				}else {
					if(!line.equals("NEXT")){
						String elements[] = line.split("-");
						MenuItem item = new MenuItem(elements[0], Double.parseDouble(elements[1]));
						loadMenu.addMenuItem(item);
					} else {
						lineNumber = 0;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error opening menu");
		}
	}
	
	public ArrayList<String> getCurrentMenuItemNames() {
		ArrayList<String> names = new ArrayList<String>();
		if(menuList.size() == 0)
			return names;
		Menu currentMenu = menuList.get((menuList.size()-1));
		for(int i = 0; i < currentMenu.getNumberOfItems(); ++i) {
			names.add(currentMenu.getItem(i).getName());
		}
		return names;
	}
	
	public ArrayList<Double> getCurrentMenuItemPrices() {
		ArrayList<Double> prices = new ArrayList<Double>();
		if(menuList.size() == 0)
			return prices;
		Menu currentMenu = menuList.get((menuList.size()-1));
		for(int i = 0; i < currentMenu.getNumberOfItems(); ++i) {
			prices.add(currentMenu.getItem(i).getPrice());
		}
		return prices;
	}
	
	public String getCurrentMenuName() {
		if(menuList.size() != 0)
			return menuList.get(menuList.size()-1).getName();
		return "No Menu";
	}
	
	public Menu getCurrentMenu() {
		return menuList.get(menuList.size()-1);
	}
	
	public void addMenu(Menu menu) {
		menuList.add(menu);
	}
	
	public void addMenuItem(String name, double price) {
		if(menuList.size() > 0) {
			menuList.get(menuList.size()-1).addMenuItem(new MenuItem(name, price));
		}
		else {
			if(managerList.size() > 0) {
				menuList.add(new Menu("default", managerList.get(0)));
				menuList.get(menuList.size()-1).addMenuItem(new MenuItem(name, price));
			}
		}
	}
	
	public void saveMenu() {
		FileWriter fstream;
		try {
			fstream = new FileWriter("menus.POS_MENU");
			BufferedWriter out = new BufferedWriter(fstream);
			for(int i = 0; i < menuList.size(); i++) {
				out.write(menuList.get(i).menuName + "-" + menuList.get(i).getCreatingManager().name + "\n");
				for(MenuItem item : menuList.get(i).getMenuItems()){
					out.write(item.name + "-" + item.price+ "\n");
				}
				if(i<menuList.size()-1)
					out.write("NEXT\n");
			}
						//out.write(name.getText()+"\n"+address.getText()+"\n"+phone.getText()+"\n"+order+"\n\n");
			//Close the output stream
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void saveManagers() {
		FileWriter fstream;
		try {
			fstream = new FileWriter("managers.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			for(int i = 0; i < managerList.size(); i++) {
				out.write(managerList.get(i).getName() + "\n");
			}
			//Close the output stream
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void saveIngredients() {
		FileWriter fstream;
		try {
			fstream = new FileWriter("ingredients.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			for(int i = 0; i < ingredients.size(); i++) {
				out.write(ingredients.get(i).toSave() + "\n");
			}
			//Close the output stream
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public ArrayList<Menu> getMenuList() {
		return this.menuList;
	}
	
	public void save() {
		saveMenu();
		saveManagers();
		saveIngredients();
	}
}