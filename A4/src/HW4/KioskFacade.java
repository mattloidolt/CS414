package HW4;

import java.util.ArrayList;

public class KioskFacade {
	private Kiosk theKiosk;
	private Restaurant theRestaurant;
	
	public KioskFacade() {
		theRestaurant = new Restaurant();
		theRestaurant.testInitialization();
	}
	
	public String getMenuName() {
		return theRestaurant.getCurrentMenuName();
	}
	
	public ArrayList<String> getCurrentMenuItemNames() {
		return theRestaurant.getCurrentMenuItemNames();
	}
	
	public ArrayList<Double> getCurrentMenuItemPrices() {
		return theRestaurant.getCurrentMenuItemPrices();
	}
}