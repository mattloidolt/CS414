package tests;

import core.MenuItem;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MenuItemTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMenuItemStringDouble() {
		MenuItem item = new MenuItem("Test", 4.99);
		assertTrue(item.price == 4.99 && item.name.equals("Test"));
	}

//	@Test
//	public void testMenuItemStringDoubleStringArray() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetName() {
		MenuItem item = new MenuItem("Test", 4.99);
		assertTrue(item.getName().equals("Test"));
	}

	@Test
	public void testGetPrice() {
		MenuItem item = new MenuItem("Test", 4.99);
		assertTrue(item.getPrice() == 4.99);
	}

}
