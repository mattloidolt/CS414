package tests;

import static org.junit.Assert.*;

import java.util.Calendar;

import junit.framework.TestCase;

import HW4.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MenuTest extends TestCase{

	Restaurant restaurant;
	Manager Bob;
	Menu menu;
	MenuItem item;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		restaurant = new Restaurant();
		Bob = new Manager("Bob", restaurant);
		menu = new Menu("Diner", Bob);
		item = new MenuItem("Test Item", 9.99);
	}

	@Test
	public void testMenu() {
		
	}

	@Test
	public void testSetEffectiveDate() {
		Calendar effectiveDate = Calendar.getInstance();
		effectiveDate.clear();
		effectiveDate.set(2012, 12, 0);
		menu.setEffectiveDate(effectiveDate);
		assertTrue(menu.getEffectiveDate() == effectiveDate);
		
	}
	

	@Test
	public void testSetIneffectiveDate() {
		Calendar inEffectiveDate = Calendar.getInstance();
		inEffectiveDate.clear();
		inEffectiveDate.set(2012, 12, 10);
		menu.setEffectiveDate(inEffectiveDate);
		assertTrue(menu.getEffectiveDate() == inEffectiveDate);
	}

	@Test
	public void testAddMenuItem() {
		menu.addMenuItem(item);
		assertTrue(menu.getMenuItems().size() == 1);
		assertTrue(menu.getMenuItems().get(0) == item);
	}

	@Test
	public void testRemoveMenuItem() {
		menu.addMenuItem(item);
		assertTrue(menu.getMenuItems().size() == 1);
		menu.removeMenuItem(item);
		assertTrue(menu.getMenuItems().size() == 0);
	}

	@Test
	public void testSetSpecial() {
		menu.addMenuItem(item);
		menu.setSpecial(item);
		assertTrue(menu.getSpecial() == item);
	}


}
