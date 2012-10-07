package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ IngredientTest.class, ManagerTest.class, MenuItemTest.class,
		MenuTest.class, OrderItemTest.class, OrderTest.class,
		PaymentTest.class, RestaurantTest.class })
public class AllTests {

}
