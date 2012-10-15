package core;

public class Manager extends Employee{
	private Restaurant restaurant;
	
	public Manager(String name, Restaurant restaurant) {
		super.name = name;
		this.setRestaurant(restaurant);
	}

	public void createMenu(String menuName) {
		Menu menu = new Menu(menuName);
		this.getRestaurant().addMenu(menu);
	}
	
	public void setSpecial(Menu menu, MenuItem item) {
		menu.setSpecial(item);
	}
	
//	public void editMenu() {
//		
//	}
	
	public String getName() {
		return super.name;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	
}
