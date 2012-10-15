package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.Manager;
import core.Menu;
import core.MenuItem;
import core.Restaurant;

public class ManagerTest {
	static Restaurant restaurant;
	static Manager bob;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		restaurant = new Restaurant();
		bob = new Manager("Bob", restaurant);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testManager() {
//		Restaurant restaurant = new Restaurant();
//		Manager bob = new Manager("Bob", restaurant);
		assertTrue(bob.name == "Bob");
		assertTrue(bob.getRestaurant() == restaurant);
	}

	@Test
	public void testCreateMenu() {
		bob.createMenu("Test");
		assertTrue(restaurant.getMenuList().size() == 1);
		assertTrue(restaurant.getMenuList().get(0).getName().equals("Test") );
		
	}

	@Test
	public void testSetSpecial() {
		Menu menu = new Menu("Test Menu", bob);
		restaurant.addMenu(menu);
		MenuItem item = new MenuItem("TestItem", 4.99);
		bob.setSpecial(menu, item);
		assertTrue(menu.getSpecial() == item);
	}

//	@Test
//	public void testEditMenu() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetName() {
		assertTrue(bob.getName().equals("Bob"));
	}

}
