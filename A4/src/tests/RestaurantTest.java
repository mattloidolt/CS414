package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import HW4.Manager;
import HW4.Menu;
import HW4.MenuItem;
import HW4.Restaurant;

public class RestaurantTest {
	Restaurant restaurant;
	Manager bob;
	Menu menu;
	MenuItem item;


	@Before
	public void setUp() throws Exception {
		restaurant = new Restaurant();
		bob = new Manager("Bob", restaurant);
		menu = new Menu("Test", bob);
		item = new MenuItem("Test Item", 5.99);
		menu.addMenuItem(item);
		restaurant.addMenu(menu);
	}

	@Test
	public void testGetCurrentMenuItemNames() {
		ArrayList<String> list = restaurant.getCurrentMenuItemNames();
		assertTrue(list.size() == 1);
		assertTrue(list.get(0)== item.name);
	}

	@Test
	public void testGetCurrentMenuItemPrices() {
		ArrayList<Double> list = restaurant.getCurrentMenuItemPrices();
		assertTrue(list.size() == 1);
		assertTrue(list.get(0) == item.price);
	}

	@Test
	public void testSetMenuList() {
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		restaurant.setMenuList(menuList);
		assertTrue(restaurant.getMenuList() == menuList);
	}

	@Test
	public void testGetCurrentMenuName() {
		assertTrue(restaurant.getCurrentMenuName().equals(menu.getName()));
	}

	@Test
	public void testGetCurrentMenu() {
		assertTrue(restaurant.getCurrentMenu() == menu);
	}

	@Test
	public void testAddMenu() {
		Menu menu1 = new Menu("menu1", bob);
		restaurant.addMenu(menu1);
		assertTrue(restaurant.getMenuList().size() == 2);
	}

	@Test
	public void testAddMenuItem() {
		restaurant.addMenuItem("test", 9.99);
		assertTrue(restaurant.getMenuList().get(0).getItem(1).name.equals("test"));
	}


	@Test
	public void testGetMenuList() {
		assertTrue(restaurant.getMenuList().size() == 1);
	}

}
