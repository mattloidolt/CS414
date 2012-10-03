package HW4;

public class Manager extends Employee{
	
	Restaurant restaurant;
	
	public Manager(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void createMenu(String menuName) {
		Menu menu = new Menu(menuName, this);
		this.restaurant.addMenu(menu);
	}
	
	public void setSpecial(Menu menu, MenuItem item) {
		menu.setSpecial(item);
	}
	
	public void editMenu() {
		
	}
	
}
