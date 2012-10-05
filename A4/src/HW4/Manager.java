package HW4;

public class Manager extends Employee{
	Restaurant restaurant;
	private String plainTextPassword;
	
	public Manager(String name, Restaurant restaurant) {
		super.name = name;
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
	
	public String getName() {
		return super.name;
	}

	
}
