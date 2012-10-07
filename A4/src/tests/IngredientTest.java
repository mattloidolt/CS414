package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import HW4.Ingredient;
import HW4.Manager;
import HW4.Menu;
import HW4.MenuItem;
import HW4.Restaurant;

public class IngredientTest {

	Restaurant restaurant;
	Manager Bob;
	Menu menu;
	MenuItem item;
	Ingredient ingredient;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Before
	public void setUp() throws Exception {
		restaurant = new Restaurant();
		Bob = new Manager("Bob", restaurant);
		menu = new Menu("Diner", Bob);
		item = new MenuItem("Test Item", 9.99);
		ingredient = new Ingredient("Test", "5", 1.5);
	}

	@Test
	public void testIngredientStringStringDouble() {
		assertTrue(ingredient.getName() == "Test" && ingredient.getUnits() == "5" && ingredient.getPriceToPurchase() == 1.5 && ingredient.getPriceToCustomer() == 1.5*1.4);
	}

	@Test
	public void testIngredientStringStringDoubleDouble() {
		Ingredient ingredient = new Ingredient("Test", "5", 1.5, 10);
		assertTrue(ingredient.getName() == "Test" && ingredient.getUnits() == "5" && ingredient.getPriceToPurchase() == 1.5 && ingredient.getPriceToCustomer() == 1.5*1.4 && ingredient.getAmountInStock() == 10);
	}

	@Test
	public void testGetName() {
		assertTrue(ingredient.getName() == "Test");
	}

	@Test
	public void testToString() {
		assertTrue(ingredient.toString().equals("Test Price: 1.5 In Stock: 0.0 5"));
	}

	@Test
	public void testToSave() {
		assertTrue(ingredient.toSave().equals("Test_5_1.5_0.0"));
	}

	@Test
	public void testPurchase() {
		assertTrue(ingredient.purchase(5) == 5*1.5);
	}

}
