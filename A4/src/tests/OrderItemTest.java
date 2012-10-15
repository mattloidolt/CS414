package tests;

import core.MenuItem;
import core.OrderItem;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrderItemTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOrderItem() {
		MenuItem menuItem = new MenuItem("Test", 4.99);
		OrderItem item = new OrderItem(menuItem);
		assertTrue(item.getItem() == menuItem);
		assertTrue(item.getQuantity() == 1);
	}

	@Test
	public void testIncrementItemQuantity() {
		MenuItem menuItem = new MenuItem("Test", 4.99);
		OrderItem item = new OrderItem(menuItem);
		item.incrementItemQuantity();
		assertTrue(item.getQuantity() == 2);
	}

	@Test
	public void testEqualsMenuItem() {
		MenuItem menuItem = new MenuItem("Test", 4.99);
		OrderItem item = new OrderItem(menuItem);
		assertTrue(item.equals(menuItem) == true);
	}

	@Test
	public void testGetItem() {
		MenuItem menuItem = new MenuItem("Test", 4.99);
		OrderItem item = new OrderItem(menuItem);
		assertTrue(item.getItem() == menuItem);
	}

}
